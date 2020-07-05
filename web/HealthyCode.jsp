<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/7/4
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html style=" height: 100%; width:100%">
<head>
    <meta charset="UTF-8">
    <title>健康码</title>
    <link rel="stylesheet" type="text/css" href="HealthyCode.css">
    <script src = "jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="HealthyCode.js"></script>
</head>
<body style="background-color :#f6f6f6">
<a href = "control.jsp" style="position: absolute; right:30px; top:20px; font-size:20px;">返回</a>
<form action="record.do" method="POST" onsubmit = "return jump()">
    <div class = "MainBody">
        <img src="images/2.png"/>
        <div class = "Question">
            <p class = "QuestionText"><span style="color: red;">*</span>1.个人信息填写</p>
            <table>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" value = "${sessionScope.name}"></td>
                </tr>
                <tr>
                    <td>身份证</td>
                    <td><input type="text" name="idcard" value = "${sessionScope.idcard}"></td>
                    <td><input type="hidden" name="identity" value = "${sessionScope.identity}"></td>
                </tr>
                <tr>
                    <td>工号或学号</td>
                    <td><input type="text" name="id" value = "${sessionScope.id}"></td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td><input type="text" name="phone"></td>
                </tr>
            </table>
        </div>
        <div class = "Question">
            <p class = "QuestionText"><span style="color: red;">*</span>2.本人近期（14天内）是否去过湖北省或重点疫区？</p>
            <div class="TheRadio">
                <p style="font-size: 7px; color: #94bff7; margin-top: 3px; text-align: center;">单选</p>
            </div>
            <div class = "RadioBox">
                <label>
                    <input type="radio" name="Leave" value="No" class = "demoHidden">
                    <span class="radioInput"></span><span class = "ChoiceText">否</span>
                </label>
            </div>
            <div class = "BreakLine"></div>
            <label>
                <div class = "RadioBox">
                    <input type="radio" name="Leave" value="Yes" class = "demoHidden">
                    <span class="radioInput"></span><span class = "ChoiceText">是</span>
                </div>
            </label>
        </div>
        <div class = "Question">
            <p class = "QuestionText"><span style="color: red;">*</span>3.本人近期（14天内）是否去过国外？</p>
            <div class="TheRadio">
                <p style="font-size: 7px; color: #94bff7; margin-top: 3px; text-align: center;">单选</p>
            </div>
            <label>
                <div class = "RadioBox">
                    <input type="radio" name="abroad" value="No" class = "demoHidden">
                    <span class="radioInput"></span><span class = "ChoiceText">否</span>
                </div>
            </label>
            <div class = "BreakLine"></div>
            <label>
                <div class = "RadioBox">
                    <input type="radio" name="abroad" value="Yes" class = "demoHidden">
                    <span class="radioInput"></span><span class = "ChoiceText">是</span>
                </div>
            </label>
        </div>
        <div class = "Question">
            <p class = "QuestionText"><span style="color: red;">*</span>4.本人近期（14天内）是否接触过新冠确诊病人或疑似病人？</p>
            <div class="TheRadio">
                <p style="font-size: 7px; color: #94bff7; margin-top: 3px; text-align: center;">单选</p>
            </div>
            <label>
                <div class = "RadioBox">
                    <input type="radio" name="contact" value="No" class = "demoHidden">
                    <span class="radioInput"></span><span class = "ChoiceText">否</span>
                </div>
            </label>
            <div class = "BreakLine"></div>
            <label>
                <div class = "RadioBox">
                    <input type="radio" name="contact" value="Yes" class = "demoHidden">
                    <span class="radioInput"></span><span class = "ChoiceText">是</span>
                </div>
            </label>
        </div>
        <div class = "Question">
            <p class = "QuestionText"><span style="color: red;">*</span>5.本人是否被卫生部门确认为新冠肺炎确诊病例或疑似病例？</p>
            <div class="TheRadio">
                <p style="font-size: 7px; color: #94bff7; margin-top: 3px; text-align: center;">单选</p>
            </div>
            <label>
                <div class = "RadioBox">
                    <input type="radio" name="diagnosis" value="No" class = "demoHidden">
                    <span class="radioInput"></span><span class = "ChoiceText">否</span>
                </div>
            </label>
            <div class = "BreakLine"></div>
            <label>
                <div class = "RadioBox">
                    <input type="radio" name="diagnosis" value="Yes" class = "demoHidden">
                    <span class="radioInput"></span><span class = "ChoiceText">是</span>
                </div>
            </label>
        </div>
        <div class = "Question">
            <p class = "QuestionText"><span style="color: red;">*</span>6.当前健康状况(若有以下状况,请在方框内勾选)</p>
            <div class="TheRadio" style="border: 1px solid #f7cca8;">
                <p style="font-size: 7px; color: #f7cca8; margin-top: 3px; text-align: center;">多选</p>
            </div>
            <label>
                <div class = "CheckBox">
                    <input type="checkbox" name ="Healthy" class = "CheckBoxHidden" id = "checkbox1" onclick="change()">
                    <span class="CheckBoxInput"></span><span class = "CheckBoxText">无异常</span>
                </div>
            </label>
            <label>
                <div class = "CheckBox">
                    <input type="checkbox" name="HealthStatus" value="Fever" class = "CheckBoxHidden" onclick="change1(this)">
                    <span class="CheckBoxInput"></span><span class = "CheckBoxText">发烧(≥37.3℃)</span>
                </div>
            </label>
            <label>
                <div class = "CheckBox">
                    <input type="checkbox" name="HealthStatus" value="Weak" class = "CheckBoxHidden" onclick="change1(this)">
                    <span class="CheckBoxInput"></span><span class = "CheckBoxText">乏力</span>
                </div>
            </label>
            <label>
                <div class = "CheckBox">
                    <input type="checkbox" name="HealthStatus" value="DryCough" class = "CheckBoxHidden" onclick="change1(this)">
                    <span class="CheckBoxInput"></span><span class = "CheckBoxText">干咳</span>
                </div>
            </label>
            <label>
                <div class = "CheckBox">
                    <input type="checkbox" name="HealthStatus" value="StuffyNose" class = "CheckBoxHidden" onclick="change1(this)">
                    <span class="CheckBoxInput"></span><span class = "CheckBoxText">鼻塞</span>
                </div>
            </label>
            <label>
                <div class = "CheckBox">
                    <input type="checkbox" name="HealthStatus" value="RunnyNose" class = "CheckBoxHidden" onclick="change1(this)">
                    <span class="CheckBoxInput"></span><span class = "CheckBoxText">流涕</span>
                </div>
            </label>
            <label>
                <div class = "CheckBox">
                    <input type="checkbox" name="HealthStatus" value="SoreThroat" class = "CheckBoxHidden" onclick="change1(this)">
                    <span class="CheckBoxInput"></span><span class = "CheckBoxText">咽痛</span>
                </div>
            </label>
            <label>
                <div class = "CheckBox">
                    <input type="checkbox" name="HealthStatus" value="Diarrhea" class = "CheckBoxHidden" onclick="change1(this)">
                    <span class="CheckBoxInput"></span><span class = "CheckBoxText">腹泻</span>
                </div>
            </label>
        </div>
        <div class = "Question">
            <p style="font-size : 13px">本人郑重承诺:</p>
            <div style="height : 35px">
                <label>
                    <input type="checkbox" name="Promise" value="Healthy" class = "CheckBoxHidden">
                    <span class="CheckBoxInput"></span>
                    <span class = "CheckBoxText" style = "width : 280px; color: #575757">为了疫情防控，本人同意以上信息已发提交所在辖区疫情防控部门统筹管理</span>
                </label>
            </div>
            <div style="height : 80px; margin-top:10px">
                <label>
                    <input type="checkbox" name="Promise" value="Healthy" class = "CheckBoxHidden">
                    <span class="CheckBoxInput"></span>
                    <span class = "CheckBoxText" style = "width : 280px; color: #575757">上述信息是我本人填写，本人对信息内容的真实性和完整性负责。如果信息有误或缺失，本人愿承担相应的的法律责任。同时，本人保证遵守防疫管控的各项规定，配合并听从各项措施和要求</span>
                </label>
            </div>
            <div style="width:100%">
                <label>
                    <input type="submit" value="提交" class="SubmitButton">
                    <div class = "SubmitInput">提交</div>
                </label>
            </div>
        </div>
    </div>
</form>
</body>
</html>

