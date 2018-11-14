package com.example.demo.bulk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Account;
import com.example.demo.hardcode.Hardcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DB2Account {

    public static void sendData2SFDC( List<Account> accounts) throws Exception{

        HashMap<String, List<Account>> accountMap = new HashMap<String, List<Account>>();
        accountMap.put("accountValue", accounts);
        String jString = JSON.toJSONString(accountMap);
        List<List<String>> listReadyToBeConvert = JSONToListString(jString);
        System.out.println(listReadyToBeConvert);
        DataChunkConverter lc = new DataChunkConverter();
        lc.map("Name", "name");
        lc.map("Phone", "phone");
        List<List<String>> listsSf = lc.listsToLists(listReadyToBeConvert);
        BulkApiHelper bah = new BulkApiHelper(BulkApiHelper.DEFAULT_TEST_ENDPOINT, Hardcode.username, Hardcode.password);
        System.out.println("listsSf:" + listsSf);

        bah.insert("Account", listsSf);


    }

    public static List<List<String>> JSONToListString(String json) {
        List<List<String>> rsList = new ArrayList<List<String>>();
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("accountValue");
        JSONObject joHeader = (JSONObject) jsonArray.get(0);
        List<String> headerList = new ArrayList<String>(joHeader.keySet());
        rsList.add(headerList);
        for (Object obj : jsonArray) {
            JSONObject jobj = (JSONObject) obj;
            List<String> contentList = new ArrayList<String>();
            for (String sourceField : headerList) {
                contentList.add(jobj.getString(sourceField));
            }
            rsList.add(contentList);
        }
        System.out.println(headerList);
        return rsList;

    }
}
