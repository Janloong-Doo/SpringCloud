/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserEntity.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-7 上午10:07
 : LastModify: 19-2-28 下午4:22
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demoall.entity;

import java.io.Serializable;

/**
 * Created by summer on 2017/5/5.
 */
public class UserEntity implements Serializable {
        private static final long serialVersionUID = -3258839839160856613L;
        private Long id;
        private String userName;
        private String passWord;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassWord() {
                return passWord;
        }

        public void setPassWord(String passWord) {
                this.passWord = passWord;
        }

        @Override
        public String toString() {
                return "UserEntity{" +
                        "id=" + id +
                        ", userName='" + userName + '\'' +
                        ", passWord='" + passWord + '\'' +
                        '}';
        }
}
