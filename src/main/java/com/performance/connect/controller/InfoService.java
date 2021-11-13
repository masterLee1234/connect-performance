package com.performance.connect.controller;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

Map<String, Object> selectInfoDetail(Map<String, Object> map);
void updateInfo(Map<String, Object> map);
void deleteInfo(Map<String, Object> map);


@Override
   public Map<String, Object> selectInfoDetail(Map<String, Object> map) {

       return InfoDAO.detailInfo(map);
   }

   @Override
   public void updateInfo(Map<String, Object> map) {
      InfoDAO.updateInfo(map);
   }
   @Override
      public void deleteInfo(Map<String, Object> map) {

          InfoDAO.deleteInfo(map);
      }
