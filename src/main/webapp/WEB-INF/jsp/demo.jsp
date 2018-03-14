<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="ctx" value="${pageContext.request.contextPath}"/>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/demo-chat.css">
    <title>微信聊天室</title>
    <script type="text/javascript">
    // JS实现选项卡切换
      window.onload=function(){
      var myTab = document.getElementById("tab2");    //整个div
      var myUl = myTab.getElementsByTagName("ul")[0];//一个节点
      var myLi = myUl.getElementsByTagName("li");    //数组
      var myDiv = myTab.getElementsByTagName("div"); //数组


      for(var i = 0; i<myLi.length;i++){
            myLi[i].index = i;
            myLi[i].onclick = function(){
                for(var j = 0; j < myLi.length; j++){
                    myLi[j].className="off";
                    myDiv[j].className = "hide";
                }
                this.className = "on";
                myDiv[this.index].className = "show";
          }
        }
      }
    </script>
  </head>
  <body>
    <div id="main">
      <div class="header clearfix">
        <div class="header-left clearfix">
          <img src="img/icon.png" alt="wechat图标">
          <p>ewei聊天室</p>
        </div>
        <div id="header-right">

        </div>
      </div>
        <div id="middle">
          <div id="tab" class="tabs-vertical">
          <!-- <div id="left"> -->
            <ul>
              <li class="chat clearfix on">
                <a href="${pageContext.request.contextPath}/chat">
                 <img src="${ctx}/img/chat.png" alt="聊天">
                 <span class="text">聊天</span>
                </a>
              </li>
              <li class="user message clearfix off">
               <a href="${pageContext.request.contextPath}/${userid}/message">
                 <img src="img/user message.png" alt="个人信息">
                 <span class="text">个人信息</span>
                 <img class="star" src="img/star.png" alt="星标">
               </a>
              </li> 
          <!--     <li class="install clearfix off">
               <a href="#">
                 <img src="img/install.png" alt="设置">
                 <span class="text">设置</span>
                 <span class="install-text"></span>
               </a>
              </li> -->
              <li class="user—install clearfix off">
                <a href="${pageContext.request.contextPath}/${userid}/config">
                <span class="block"></span>
                <img src="img/user install.png" alt="个人设置">
                <span class="text">个人设置</span>
                </a>
              <li class="system—install clearfix off">
                <a href="${ctx}/system-setting">
                 <span class="block"></span>
                 <img src="img/system install.png" alt="系统设置">
                <span class="text">系统设置</span>
                </a>
              <li class="system diary clearfix off">
                <a href="${pageContext.request.contextPath}/${userid}/log">
                <img src="img/system diary.png" alt="系统日志">
                <span class="text">系统日志</span>
                </a>
              </li>
              <li class="help clearfix off"><a href="${pageContext.request.contextPath}/about">
                <img src="img/help.png" alt="帮助">
                <span class="text">关于</span>
                </a>
              </li>
              <!-- <li class="about clearfix off"><a href="#">
                <img src="img/about.png" alt="关于">
                <span class="text">帮助</span>
                </a>
              </li> -->
              <li class="cancel clearfix off"><a href="${pageContext.request.contextPath}/logout">
               <img src="img/cancel.png" alt="注销">
               <span class="text">注销</span>
                </a>
              </li>
              <div class="left-footer">
                <div class="welcome clearfix">
                  <div class="welcome-icon clearfix">
                   <img src="img/welcome.png" alt="标签">
                   <p>welcome</p>
                  </div>
                  <p>欢迎使用ewei聊天室( •̀∀•́ )</p>
                </div>
              </div>
            </ul>
          </div>
         <!-- 聊天 -->
            <div class="tabs-content-placeholder clearfix">
                <!-- <div id="content-chat" class="tab-content-active clearfix">
                  <div id="chat-middle">
                    <div id="message-logging">

                    </div>
                    <div id="editor">
                      <textarea name="name" rows="8" cols="80">这里输入你想要的内容....</textarea>
                    </div>
                    <div id="function">
                      <div class="receiver">
                        <p>发送给：全体成员</p>
                        <input type="button" name="" value="复位">
                      </div>
                      <div class="function-type">
                        <button type="button" name="button"><img src="img/send.png" alt="发送"><p>发送</p></button>
                        <button type="button" name="button"><img src="img/clean.png" alt="清屏"><p>清屏</p></button>
                        <button type="button" name="button"><img src="img/check.png" alt="检查"><p>检查</p></button>
                        <button type="button" name="button"><img src="img/close.png" alt="断开"><p>断开</p></button>
                        <button type="button" name="button"><img src="img/connect.png" alt="连接"><p>连接</p></button>
                      </div>
                    </div>
                  </div>

                  <div id="chat-right">
                    <div id="online-list">

                    </div>
                  </div>
                </div> -->
                <!-- 个人信息 -->
               <%--  <div id="content-usermessage" class="clearfix">
                   <div class="top clearfix">
                     <p class="usertext1">个人设置</p>
                   </div>
                   <div id= "tab2">
                       <ul>
                       <li class="on">基本信息</li>
                       <li class="off">修改头像</li>
                       <li class="off">修改密码</li>
                       </ul>
                      <div id="basemessage" class="show">
                        <form class="" action="${pageContext.request.contextPath } /${userid}/update" method="post">
                          <label class="label-first" for="username">用户名</label><input id="userid" class="username field" type="text" name="userid" value=""><br>
                          <label for="nickname">昵称</label><input id="nickname" class="nickname field" type="text" name="nickname" value=""><br>
                          <label for="sex">性别</label><select id="sex" class="sex" name="sex">
                            <option selected value="1">男</option>
                            <option value="2">女</option>
                          </select> <br>
                          <label for="age">年龄</label><input id="age" class="age field" type="text" name="age" value=""><br>
                          <label class="label-last" for="profile">个性签名</label><textarea id="profile" class="profile" name="profile" rows="8" cols="80"></textarea>
                          <br>
                          <input class="submit" type="submit" name="" value="提交">
                        </form>
                      </div>
                      <div id="secondPage" class="hide">
                          <form class="" action="${pageContext.request.contextPath } /${userid}/upload" enctype="multipart/form-data">

                          </form>
                      </div>
                      <div id="thirdPage" class = "hide">
                          <form class="" action="${pageContext.request.contextPath } /${userid}/editPassword" enctype="post">

                          </form>
                      </div>
                    </div>
                 </div> --%>
                <!-- 个人设置 -->
                <!-- <div id="content-userinstall" class="clearfix">
                  <div class="top2 clearfix">
                    <p class="usertext1">系统设置</p><p class="usertext2">/form</p>
                  </div>
                  <div id= "tab3">
                      <ul>
                      <li class="on">基本设置</li>
                      <li class="off">修改头像</li>
                      <li class="off">修改密码</li>
                      </ul>
                     <div id="basemessage" class="show">

                     </div>
                     <div id="secondPage" class="hide">
                         <form class="" action="${pageContext.request.contextPath } /${userid}/upload" enctype="multipart/form-data">

                         </form>
                     </div>
                     <div id="thirdPage" class = "hide">
                         <form class="" action="${pageContext.request.contextPath } /${userid}/editPassword" enctype="post">

                         </form>
                     </div>
                   </div>
                </div> -->
                <!-- 系统设置 -->
                <div id="content-systeminstall" class=""></div>
                <!-- 系统日志 -->
                <div id="content-systemdiary" class=""></div>

            </div>

          </div>
      </div>
    </div>
  </body>
</html>