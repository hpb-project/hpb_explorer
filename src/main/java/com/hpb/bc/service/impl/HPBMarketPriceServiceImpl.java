/*
 * Copyright 2020 HPB Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hpb.bc.service.impl;


import com.hpb.bc.entity.HpbMarketPrice;
import com.hpb.bc.mapper.HpbMarketPriceMapper;
import com.hpb.bc.model.HpbMarketPriceModel;
import com.hpb.bc.service.HPBMarketPriceService;
import com.hpb.bc.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("marketPriceService")
public class HPBMarketPriceServiceImpl implements HPBMarketPriceService {
    @Autowired
    private HpbMarketPriceMapper hpbMarketPriceMapper;


    @Override
    public void addNewPrice(HpbMarketPrice record) {
        if (hpbMarketPriceMapper.selectByDate(record.getPriceTime()) == null) {
            hpbMarketPriceMapper.insertSelective(record);
        }
    }

    /**
     * 原来浏览器使用接口；
     */
    @Override
    public Map<String, Object> queryEchartData() {
        List<HpbMarketPrice> price = hpbMarketPriceMapper.selectEchartData();
        List<BigDecimal> prices = new ArrayList<>();
        List<BigDecimal> usds = new ArrayList<>();
        List<String> times = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        price.stream().forEach(hpbMarketPrice -> {
            prices.add(hpbMarketPrice.getPrice());
            usds.add(hpbMarketPrice.getPriceUsd());
            times.add(sdf.format(hpbMarketPrice.getPriceTime()));
        });
        Map<String, Object> result = new HashMap<>();
        Collections.reverse(prices);
        Collections.reverse(usds);
        Collections.reverse(times);
        result.put("prices", prices);
        result.put("usds", usds);
        result.put("times", times);
        return result;
    }

    /**
     * 新浏览器使用接口；改版浏览器使用接口
     */
    @Override
    public List<HpbMarketPriceModel> queryNewEchartData() {
        List<HpbMarketPrice> price = hpbMarketPriceMapper.selectEchartData();
        List<HpbMarketPriceModel> priceBeanList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        price.stream().forEach(hpbMarketPrice -> {
            HpbMarketPriceModel model = new HpbMarketPriceModel();
            model.setTimestamp(DateUtils.dateToUnixTimestamp(hpbMarketPrice.getPriceTime()));
            model.setPrice(hpbMarketPrice.getPrice() + "");
            model.setPriceUsd(hpbMarketPrice.getPriceUsd() + "");
            priceBeanList.add(model);
        });
        return priceBeanList;
    }
}
