package com.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tool.ImageToBase64;
/**
 * Created by sunh on 2020/10/24.
 */
public class ImageToBase64Function extends AbstractFunction{

    /**
     * 执行方法
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    private Object[] values;
    private CompoundVariable imgPath;//存储第参数

    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        imgPath = (CompoundVariable) values[0];
        ImageToBase64 itb=new ImageToBase64();
        String b64 = itb.ImageToBase64(imgPath.execute().trim());
        return String.valueOf(b64);
    }

    /**
     * 设置参数。接收用户传递的参数
     * @param collection
     * @throws InvalidVariableException
     */
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        checkParameterCount(collection,1);
        values = collection.toArray();
//        System.out.println("setParameters run!!!!");
    }

    /**
     * 功能名称
     * @return
     */
    public String getReferenceKey() {
        return "__ImageToBase64";
    }

    /**
     * 功能描述，参数描述
     * @return
     */
    public List<String> getArgumentDesc() {
//        System.out.println("getArgumentDesc run !!!!!");
        List desc = new ArrayList();
        desc.add("图片地址");
        return desc;
    }
}
