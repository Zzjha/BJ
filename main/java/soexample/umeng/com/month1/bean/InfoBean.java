package soexample.umeng.com.month1.bean;

/**
 * author:author${朱佳华}
 * data:2019/1/18
 */
public class InfoBean {

    /**
     * msg : 获取用户信息成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":"B3F4F02FA7852C16B27E0FDA9A65886A","createtime":"2019-01-18T09:54:08","email":null,"fans":16,"follow":6,"gender":0,"icon":"https://www.zhaoapi.cn/images/1547633854030checked.gif","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"张三","password":"016AD198F61ADB3CEFA4E5ECD78604FA","praiseNum":null,"token":"5A4143DE8859115F3B4C510056C9B4A7","uid":71,"userId":null,"username":"18612991023"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : B3F4F02FA7852C16B27E0FDA9A65886A
         * createtime : 2019-01-18T09:54:08
         * email : null
         * fans : 16
         * follow : 6
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/1547633854030checked.gif
         * latitude : null
         * longitude : null
         * mobile : 18612991023
         * money : 0
         * nickname : 张三
         * password : 016AD198F61ADB3CEFA4E5ECD78604FA
         * praiseNum : null
         * token : 5A4143DE8859115F3B4C510056C9B4A7
         * uid : 71
         * userId : null
         * username : 18612991023
         */

        private Object age;
        private Object appkey;
        private String appsecret;
        private String createtime;
        private Object email;
        private int fans;
        private int follow;
        private int gender;
        private String icon;
        private Object latitude;
        private Object longitude;
        private String mobile;
        private int money;
        private String nickname;
        private String password;
        private Object praiseNum;
        private String token;
        private int uid;
        private Object userId;
        private String username;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public Object getAppkey() {
            return appkey;
        }

        public void setAppkey(Object appkey) {
            this.appkey = appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
