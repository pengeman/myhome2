#!/bin/bash
# 更新本地的 release 分支
git fetch origin release
# 切换到本地的 release 分支
git checkout release
# 合并远端的 main 分支到本地的 release 分支
git merge origin/main
# 推送本地的 release 分支到远端的 release 分支
git push
# 切换回 main 分支
git checkout main
