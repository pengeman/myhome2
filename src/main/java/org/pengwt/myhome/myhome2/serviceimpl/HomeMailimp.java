package org.pengwt.myhome.myhome2.serviceimpl;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.protocol.IMAPProtocol;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.pengwt.myhome.myhome2.service.HomeMail;
import org.springframework.util.ObjectUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeMailimp implements HomeMail {
    public static String NORM_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
    private MimeMessage mimeMessage;
    /**
     * 附件下载后的存放目录
     */
    private String saveAttachPath = "";
    /**
     * 存放邮件内容的StringBuffer对象
     */
    private StringBuffer bodyText = new StringBuffer();

    /**
     * 构造函数,初始化一个MimeMessage对象
     *
     * @param mimeMessage
     */
    public void ShowMail(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    /**
     * 获得发件人的地址和姓名
     *
     * @return
     * @throws MessagingException
     */
    public String getFrom() throws MessagingException {
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();
        String from = address[0].getAddress();
        if (from == null) {
            from = "";
        }
        String personal = address[0].getPersonal();

        if (personal == null) {
            personal = "";
        }

        String fromAddr = null;
        if (personal != null || from != null) {
            fromAddr = personal + "<" + from + ">";
        }
        return fromAddr;
    }

    /**
     * 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同
     *
     * @param type "to"----收件人　"cc"---抄送人地址　"bcc"---密送人地址
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public String getMailAddress(String type) throws MessagingException, UnsupportedEncodingException {
        if (ObjectUtils.isEmpty(type)) {
            return "";
        }

        String addType = type.toUpperCase();

        if (!addType.equals("TO") && !addType.equals("CC") && !addType.equals("BCC")) {
            return "";
        }
        InternetAddress[] address;

        if (addType.equals("TO")) {
            address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);
        } else if (addType.equals("CC")) {
            address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);
        } else {
            address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC);
        }

        if (ObjectUtils.isEmpty(address)) {
            return "";
        }
        StringBuilder mailAddr = new StringBuilder();
        String emailAddr;
        String personal;
        for (int i = 0; i < address.length; i++) {
            emailAddr = address[i].getAddress();
            if (emailAddr == null) {
                emailAddr = "";
            } else {
                emailAddr = MimeUtility.decodeText(emailAddr);
            }
            personal = address[i].getPersonal();
            if (personal == null) {
                personal = "";
            } else {
                personal = MimeUtility.decodeText(personal);
            }
            mailAddr.append(",").append(personal).append("<").append(emailAddr).append(">");
        }
        return mailAddr.toString().substring(1);
    }

    /**
     * 获得邮件主题
     *
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public String getSubject() throws MessagingException, UnsupportedEncodingException {
        String subject = mimeMessage.getSubject();
        System.out.println(subject);
        subject = MimeUtility.decodeText(subject);
        if (subject == null) {
            subject = "";
        }
        return subject;
    }

    /**
     * 获得邮件发送日期
     *
     * @return
     * @throws MessagingException
     */
    public String getSentDate() throws MessagingException {
        Date sentDate = mimeMessage.getSentDate();
        SimpleDateFormat format = new SimpleDateFormat(NORM_DATETIME_PATTERN);
        return format.format(sentDate);
    }

    /**
     * 获得邮件正文内容
     *
     * @return
     */
    public String getBodyText() {
        return bodyText.toString();
    }

    /**
     * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件
     * 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
     * @param part
     * @throws MessagingException
     * @throws IOException
     */
    public void getMailContent(Part part) throws MessagingException, IOException {

        String contentType = part.getContentType();

        int nameIndex = contentType.indexOf("name");

        boolean conName = false;

        if (nameIndex != -1) {
            conName = true;
        }

        if (part.isMimeType("text/plain") && conName == false) {
            bodyText.append((String) part.getContent());
        } else if (part.isMimeType("text/html") && conName == false) {
            bodyText.append((String) part.getContent());
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                this.getMailContent(multipart.getBodyPart(i));
            }
        } else if (part.isMimeType("message/rfc822")) {
            this.getMailContent((Part) part.getContent());
        }
    }

    /**
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"
     *
     * @return
     * @throws MessagingException
     */
    public boolean getReplySign() throws MessagingException {

        boolean replySign = false;

        String needReply[] = mimeMessage.getHeader("Disposition-Notification-To");

        if (needReply != null) {
            replySign = true;
        }
        return replySign;
    }

    /**
     * 判断此邮件是否已读，如果未读返回false,反之返回true
     *
     * @return
     * @throws MessagingException
     */
    public boolean isNew() throws MessagingException {
        boolean isNew = false;
        Flags flags = mimeMessage.getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == Flags.Flag.SEEN) {
                isNew = true;
            }
        }
        return isNew;
    }

    /**
     * 判断此邮件是否包含附件
     *
     * @param part
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public boolean isContainAttach(Part part) throws MessagingException, IOException {
        boolean attachFlag = false;
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            BodyPart mPart;
            String conType;
            for (int i = 0; i < mp.getCount(); i++) {
                mPart = mp.getBodyPart(i);
                String disposition = mPart.getDisposition();
                if (Part.ATTACHMENT.equals(disposition) || Part.INLINE.equals(disposition)) {
                    attachFlag = true;
                } else if (mPart.isMimeType("multipart/*")) {
                    attachFlag = this.isContainAttach(mPart);
                } else {
                    conType = mPart.getContentType();
                    if (conType.toLowerCase().indexOf("application") != -1 || conType.toLowerCase().indexOf("name") != -1){
                        attachFlag = true;
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            attachFlag = isContainAttach((Part) part.getContent());
        }
        return attachFlag;
    }

    /**
     * 保存附件
     *
     * @param part
     * @throws MessagingException
     * @throws IOException
     */
    public void saveAttachMent(Part part) throws MessagingException, IOException {
        String fileName;
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            BodyPart mPart;
            for (int i = 0; i < mp.getCount(); i++) {
                mPart = mp.getBodyPart(i);
                String disposition = mPart.getDisposition();
                if (Part.ATTACHMENT.equals(disposition) || Part.INLINE.equals(disposition)) {
                    fileName = mPart.getFileName();
                    if (null != fileName && fileName.toLowerCase().indexOf("gb2312") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                    }
                    this.saveFile(fileName, mPart.getInputStream());
                } else if (mPart.isMimeType("multipart/*")) {
                    this.saveAttachMent(mPart);
                } else {
                    fileName = mPart.getFileName();
                    if ((fileName != null) && (fileName.toLowerCase().indexOf("GB2312") != -1)) {
                        fileName = MimeUtility.decodeText(fileName);
                        this.saveFile(fileName, mPart.getInputStream());
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            this.saveAttachMent((Part) part.getContent());
        }
    }

    /**
     * 设置附件存放路径
     *
     * @param attachPath
     */
    public void setAttachPath(String attachPath) {
        this.saveAttachPath = attachPath;
    }

    /**
     * 获得附件存放路径
     *
     * @return
     */
    public String getAttachPath() {
        return saveAttachPath;
    }

    /**
     * 真正的保存附件到指定目录里
     *
     * @param fileName
     * @param in
     * @throws IOException
     */
    private void saveFile(String fileName, InputStream in) throws IOException {
        String osName = System.getProperty("os.name");
        String storeDir = this.getAttachPath();
        if (null == osName) {
            osName = "";
        }
        if (osName.toLowerCase().indexOf("win") != -1) {
            if (ObjectUtils.isEmpty(storeDir))
                storeDir = "C:\\tmp";
        } else {
            storeDir = "/tmp";
        }
//        fileName=fileName.replace("=?", "");
//        fileName=fileName.replace("?=", "");
//        fileName = fileName.substring(fileName.length() - 6, fileName.length());
        FileOutputStream fos = new FileOutputStream(new File(storeDir + File.separator + fileName));
        IOUtils.copy(in, fos);
        IOUtils.closeQuietly(fos);
        IOUtils.closeQuietly(in);
    }

    /**
     * 获取163邮箱信息
     *
     * @param host
     * @param username
     * @param password
     * @param protocol
     * @return
     * @throws MessagingException
     */
    public static Message[] getWEMessage(String host, String username, String password, String protocol) throws MessagingException {
        //创建属性对象
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", protocol);
        //创建会话
        Session session = Session.getDefaultInstance(props, null);
        //存储对象
        Store store = session.getStore(protocol);
        //连接
        store.connect(host, username, password);
        //创建目录对象
        Folder folder = store.getFolder("INBOX");
        if (folder instanceof IMAPFolder) {
            IMAPFolder imapFolder = (IMAPFolder)folder;
            //javamail中使用id命令有校验checkOpened, 所以要去掉id方法中的checkOpened();
            imapFolder.doCommand(new IMAPFolder.ProtocolCommand() {
                public Object doCommand(IMAPProtocol p) throws com.sun.mail.iap.ProtocolException {
                    p.id("FUTONG");
                    return null;
                }
            });
        }
        if(folder != null) {
            folder.open(Folder.READ_WRITE);
        }
        return folder.getMessages();
    }


    /**
     * 得到189邮箱消息
     * @param host
     * @param username
     * @param password
     * @param protocol
     * @return
     * @throws MessagingException
     */
    public static Message[] get189Message(String host, String username, String password, String protocol, String sfolder) throws MessagingException {
        //创建属性对象
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", protocol);
        //创建会话
        Session session = Session.getDefaultInstance(props, null);
        //存储对象
        Store store = session.getStore(protocol);
        //连接
        store.connect(host, username, password);
        //创建目录对象
        Folder folder = store.getFolder(sfolder);
        if (folder instanceof IMAPFolder) {
            IMAPFolder imapFolder = (IMAPFolder)folder;
            System.out.println(imapFolder.getFullName());
            //javamail中使用id命令有校验checkOpened, 所以要去掉id方法中的checkOpened();
            imapFolder.doCommand(new IMAPFolder.ProtocolCommand() {
                public Object doCommand(IMAPProtocol p) throws com.sun.mail.iap.ProtocolException {
                    p.id("FUTONG");
                    return null;
                }
            });
        }
        if(folder != null) {
            folder.open(Folder.READ_WRITE);
        }
        return folder.getMessages();
    }

    /**
     * 获取qq邮箱信息
     *
     * @param host
     * @param username
     * @param password
     * @param protocol
     * @return
     * @throws MessagingException
     */
    public static Message[] getQQMessage(String host, String username, String password, String protocol) throws MessagingException {
        //创建属性对象
        Properties props = new Properties();
        props.put("mail.store.protocol", protocol);
        //创建会话
        Session session = Session.getDefaultInstance(props, null);
        //存储对象
        Store store = session.getStore(protocol);
        //连接
        store.connect(host,username,password);
        //创建目录对象
        Folder folder = store.getFolder("Inbox");
        if(folder != null) {
            folder.open(Folder.READ_WRITE);
        }
        return folder.getMessages();
    }

    /**
     * 过滤邮箱信息
     *
     * @param messages
     * @param fromMail 只读取该邮箱发来的邮件，如果为空则不过滤
     * @param startDate 只读取该日期以后的邮件，如果为空则不过滤
     * @return
     * @throws MessagingException
     */
    public static List<Message> filterMessage(Message[] messages, String fromMail, String startDate) throws MessagingException, ParseException {
        List<Message> messageList = new ArrayList<>();
        if (ObjectUtils.isEmpty(messages)) {
            return messageList;
        }
        boolean isEnptyFromMail = ObjectUtils.isEmpty(fromMail);
        boolean isEnptyStartDate = ObjectUtils.isEmpty(startDate);
        if (isEnptyFromMail && isEnptyStartDate) {
            return Arrays.asList(messages);
        }

        String from;
        for (Message message: messages) {
            from = null;
            if(message.getFrom() != null) {
                from = (message.getFrom()[0]).toString();
            }
            if (isEnptyFromMail) {
                if (message.getSentDate() != null && new SimpleDateFormat(NORM_DATETIME_PATTERN).parse(startDate).getTime() > message.getSentDate().getTime()) {
                    continue;
                }
            } else if (null != from && from.contains(fromMail)) {
                if (!isEnptyStartDate && new SimpleDateFormat(NORM_DATETIME_PATTERN).parse(startDate).getTime() > message.getSentDate().getTime()) {
                    continue;
                }
            } else {
                continue;
            }
            messageList.add(message);
        }
        return messageList;
    }

    /**
     * 打印邮件
     *
     * @param messageList
     * @throws IOException
     * @throws MessagingException
     */
    public static void printMailMessage(List<Message> messageList) throws IOException, MessagingException {
        System.out.println("邮件数量:" + messageList.size());
        HomeMailimp re = new HomeMailimp();
        //ShowMail re;

        Message message;
        for (int i = 0, leng = messageList.size(); i < leng; i++) {
            message = messageList.get(i);
            //re = new ShowMail((MimeMessage) message);
            re.ShowMail((MimeMessage) message);
            System.out.println("邮件【" + i + "】主题:" + re.getSubject());
            System.out.println("邮件【" + i + "】发送时间:" + re.getSentDate());
            System.out.println("邮件【" + i + "】是否需要回复:" + re.getReplySign());
            System.out.println("邮件【" + i + "】是否已读:" + re.isNew());
            System.out.println("邮件【" + i + "】是否包含附件:" + re.isContainAttach( message));
            System.out.println("邮件【" + i + "】发送人地址:" + re.getFrom());
            System.out.println("邮件【" + i + "】收信人地址:" + re.getMailAddress("to"));
            System.out.println("邮件【" + i + "】抄送:" + re.getMailAddress("cc"));
            System.out.println("邮件【" + i + "】暗抄:" + re.getMailAddress("bcc"));
            System.out.println("邮件【" + i + "】发送时间:" + re.getSentDate());
            System.out.println("邮件【" + i + "】邮件ID:" + ((MimeMessage) message).getMessageID());
            re.getMailContent(message);
            System.out.println("邮件【" + i + "】正文内容:\r\n" + re.getBodyText());
            re.setAttachPath("D:\\Download\\mailFile\\");
            re.saveAttachMent(message);
        }
    }


    @Override
    public Message[] recieve(String host, String username, String pwd, String foldername) {
Message[] messages = new Message[0];
        return messages;
    }

    @Override
    public Message recieve(String host, String username, String pwd, String folder, String subject) {
        return null;
    }

    @Override
    public void send(String host, String username, Message message) {

    }


    public static void main(String[] args) throws MessagingException, IOException, ParseException {
        //189登录信息
        //邮件服务器
        String host = "pop.189.cn";
        //邮箱账号
        String username = "18935183641";
        //授权码zL=3yH$2B@6rW*4t
        String password = "zL=3yH$2B@6rW*4t";
        //协议
        String protocol = "imap";
        //只读取该邮箱发来的邮件
        String fromMail = null;
        //只读取该日期以后的邮件
        String startDate = null;
        List<Message> messageList = filterMessage(get189Message(host, username, password, protocol, "INBOX"), fromMail, startDate);
        printMailMessage(messageList);

        //qq登录信息
        String host2 = "imap.qq.com";
        String username2 = "xx";
        String password2 = "yy";
        // String protocol2 = "imaps";
        String protocol2 = "pop3";
        String fromMail2 = null;
        String startDate2 = null;
        //List<Message> messageList2 = filterMessage(getQQMessage(host2, username2, password2, protocol2), fromMail2, startDate2);
        //printMailMessage(messageList2);
    }

}
