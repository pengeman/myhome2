# myhome2

我的首页,收藏我喜欢的网站

http://localhost:8001/myhome
http://myhome.sucicada.cf/myhome

## For Development

### branch 说明

|          |                            |
|----------|----------------------------|
| release: | 正式发布, 用于GitHub Action 自动构建 | 
| main:    | 开发                         |

### how to build

```bash
mvn clean package -Dmaven.test.skip=true -T4
```

### local test

```bash
java -jar target/myhome2-0.0.1-SNAPSHOT.jar 
```
