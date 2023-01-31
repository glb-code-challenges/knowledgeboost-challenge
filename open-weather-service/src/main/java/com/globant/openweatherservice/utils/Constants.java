package com.globant.openweatherservice.utils;

public class Constants {
    public enum ResponseConstant{
        SUCCESS("Success"),
        FAILURE("Failure");

        private String description;

        ResponseConstant(final String description){
            this.description = description;
        }

        public String getDescription(){
            return this.description;
        }
    }
}
