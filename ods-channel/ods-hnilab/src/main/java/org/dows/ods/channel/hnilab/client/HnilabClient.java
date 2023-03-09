package org.dows.ods.channel.hnilab.client;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.ods.api.ChannelConfig;
import org.dows.ods.channel.hnilab.HnilabResponse;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 集微营销短信发送
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class HnilabClient  {
    private final HnilabEndpoint hnilabEndpoint;

    public HnilabResponse doSend(String methodName,Object params) {

        if(methodName.equals("saveOperateRecordData")) {
            /**
             * 构建请求参数
             */
            //hnilabEndpoint.buildParams(msgModel, channelConfig, msgSignature);
            hnilabEndpoint.saveOperateRecordData(null).enqueue(new Callback<HnilabResponse>() {
                @Override
                public void onResponse(Call<HnilabResponse> call, Response<HnilabResponse> response) {

                }

                @Override
                public void onFailure(Call<HnilabResponse> call, Throwable throwable) {

                }
            });
        }
        return null;
    }


    private void handleResponseData(){
    }
}

