package com.globant.talentpool.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rain {
    @SerializedName("1h")
    @Expose
    private Double oneHour;
    @SerializedName("3h")
    @Expose
    private Double threeHours;
}
