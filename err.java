private void readFile()
	{
		try 
		{     DbEnv dbEnv = new DbEnv();
		      SQLExecuter sqlExce = dbEnv.getTestTpasExecuter("DW");
		      String customer_id = "570";
		      StringBuffer sb = new StringBuffer();
		      StringBuffer sb_data = new StringBuffer();
			  String path = "C:\\MH_USE\\my_request\\insert\\";
			  File myObj = new File(path+customer_id+"_insert.txt");
			
		      Scanner myReader = new Scanner(myObj);
		      ArrayList<String> prodInfoList = new ArrayList<String>();
		      while (myReader.hasNextLine()) 
		      {
		        String data = myReader.nextLine();
		        if (data.startsWith("add prod_info:"))
		        	prodInfoList.add(data);
		      }
		      myReader.close();
		      
		      ArrayList<Object[]> objList = new ArrayList<Object[]>();
		      for (String prodInfo : prodInfoList)
		      {
		    	  String[] s =(prodInfo.substring(prodInfo.indexOf(":")+1)).split(",");
		    	  String prdgrp_id = s[0];
		    	  String ec_type = s[1];
		    	  String ec_no = s[2];
		    	  String op_name = s[3];
		    	  sb_data.append(prdgrp_id+","+ec_type+","+ec_no+","+op_name+"\n");
		    	  String sql = "select * from prod_info where prdgrp_id='"+prdgrp_id+"' and ec_type='"+ec_type+"'"+ " and ec_no='"+ec_no+"' and op_name='"+op_name+"' and waf_sampling_rule='' ";
		    	 // System.out.println(sql);
		    	  RowList rl = sqlExce.doQuery(sql);
		    	  if (rl != null && rl.getCount() == 1)
		    	  { 
		    		  String logSql = "update prod_info set waf_sampling_rule='1:NA(Full wafer list to test)' where prdgrp_id='"+prdgrp_id+"' and ec_type='"+ec_type+"'"+ " and ec_no='"+ec_no+"' and op_name='"+op_name+"' and waf_sampling_rule=''  ";
		    		  sb.append(logSql + "\n");
		    		  String update = "update prod_info set waf_sampling_rule='1:NA(Full wafer list to test)' where prdgrp_id=? and ec_type=? and ec_no=? and op_name=? and waf_sampling_rule=''  ";
		    		  objList.add(new Object[]{prdgrp_id, ec_type, ec_no, op_name});
		    	  }
		    	  
		    	  
		      }
		      System.out.println(sb.toString());
		      System.out.println(objList.size() +"==> prodInfoList =" + prodInfoList.size());
		      
		      
		      String bakPath = "C:\\MH_USE\\my_request\\insert\\prod_info\\";
			  File prod = new File(bakPath+customer_id+".txt");
		      if (prod.createNewFile()) 
		        System.out.println("File created: " + prod.getName());
		      else 
		        System.out.println("File already exists.");
		      
		      FileWriter myWriter = new FileWriter(bakPath+customer_id+".txt");
		      myWriter.write(sb_data.toString());
		      myWriter.close();
		}
		catch (Exception e)
		{
			
		}
	}
