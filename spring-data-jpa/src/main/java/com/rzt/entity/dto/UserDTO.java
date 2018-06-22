package com.rzt.entity.dto;


import java.io.Serializable;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-12 10:06
 */
public class UserDTO implements Serializable {

    private String name;
    private String address;

    public UserDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
