package io.github.asyncomatic.starter.examples;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class SampleState {
    @SerializedName("config")
    private Map<String,Object> config = new HashMap<>();

    @SerializedName("data")
    private Map<String,Object> data = new HashMap<>();

}
