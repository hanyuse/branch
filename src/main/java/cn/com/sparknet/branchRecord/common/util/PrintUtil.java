package cn.com.sparknet.branchRecord.common.util;

import java.util.List;
import java.util.Map;


/**
 * 文书打印公用类
 * @author wangww  2015.09.12
 */
public class PrintUtil {
    /**
     * 核发《食品经营许可证》情况登记表
     * @param bodyWenShu
     * @param title
     * @return
     */
    public static String getHFDJBwenshu(String SEND_NAME,String SEND_DATE, String COPY_NUM,String title ){
        StringBuffer HFDJBwenshu = new StringBuffer();

        HFDJBwenshu.append( "<div class='ws_formatprint_template_djb'>" );
        HFDJBwenshu.append( "<h1 class='djb_title1 '>"+title+"</h1>" );
        HFDJBwenshu.append( "<div class='djb_table'><table> <tr><th style='width:120px'>发证人员</th><td>"+SEND_NAME+"</td><th  style='width:120px'>发证日期</th><td>"+SEND_DATE+"</td></tr>" );
        HFDJBwenshu.append( "<tr height='150px'> <th><p class='t_c'>领取许可证<br/>情况</p></th> <td  colspan='3'>" );
        HFDJBwenshu.append( "<div class='csyj'><p>本人领取了许可证正本    1  份，副本   "+COPY_NUM+"  份。</p>" );
        HFDJBwenshu.append( "<div class='t_r'><p class='qianm'>领取人签字（盖章）：<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" );
        HFDJBwenshu.append( "年　　月　　日<p></div></div></td></tr>" );
        HFDJBwenshu.append( "<tr height='400px'> <th colspan='4'>（领取人身份证明复印件粘贴处）</th></tr>" );
        HFDJBwenshu.append( "<tr height='100px'> <th>备注</th><td colspan='3'></td></tr></table></div>" );
        HFDJBwenshu.append( "</div>" );

        return HFDJBwenshu.toString();
    }
    
    /**
     * 归档记录表
     * @param fileTitle 
     * @param bodyWenShu
     * @param title
     * @return
     */
    public static String getGuiDangwenshu( String FILING_NO, String FILING_DATE, String LIC_NO, String OPER_MAN_NAME, String CORP_NAME, String OPERATE, List<Map<String, Object>> listFile, String title ){
        StringBuffer GuiDangwenshu = new StringBuffer();
        GuiDangwenshu.append( "<div class='print_template_djb'>" );

        GuiDangwenshu.append( "<h1 class='djb_title1'>"+title+"</h1>" );
        GuiDangwenshu.append( "<div class='djb_table1'><table style='border-bottom:0'><tr><th colspan='4'>归档基本信息</th></tr><tr><th style='width:120px;'>档案号</th><td>"+FILING_NO+"</td><th style='width:120px;'>归档日期</th><td>"+FILING_DATE+"</td></tr>" );
        GuiDangwenshu.append( "<tr><th>许可证编号</th><td>"+LIC_NO+"</td><th><p class='t_c'>法定代表人<br>（负责人）</p></th><td>"+OPER_MAN_NAME+"</td></tr>" );
        GuiDangwenshu.append( "<tr><th>名称</th><td><p>"+CORP_NAME+"</p></td><th>业务类型</th><td>"+OPERATE+"</td></tr></table>" );
        GuiDangwenshu.append( "<table style='border-top:0;table-layout:inherit;'><tr><th colspan='3' style='border-top:0'>归档材料清单</th></tr>" );
        GuiDangwenshu.append( "<tr><th style='width:50px;'>序号</th><th style='width:650px;'>材料名称</th><th style='width:50px;'>页数</th></tr>" );
          if(listFile.size()>0) {
                    for(int i=0;i<listFile.size();i++) {
                        GuiDangwenshu.append( "<tr><th>"+listFile.get( i ).get( "XH" )+"</th><td><p>"+listFile.get( i ).get( "FILE_NAME" )+"</p></td><th>"+listFile.get( i ).get( "PAGE_NUM" )+"</th></tr>" );
                    }
          }else {
              GuiDangwenshu.append( "<tr height='100px'><th colspan='3'>（没有需要归档的材料）</th></tr>" );
          }
        GuiDangwenshu.append( "</table></div>" );
        GuiDangwenshu.append( "</div>" );

        return GuiDangwenshu.toString();
    }
    
    /**
     * 将文书包在完整页面
     * @param bodyWenShu(文书内容)
     * @param title(文书标题)
     * @return
     */
    public static String getCommonWenShu( String bodyWenShu, String title ,String contextPath) {
        StringBuffer commonWenShu = new StringBuffer();
        commonWenShu.append( "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>" );
        commonWenShu.append( "<html xmlns='http://www.w3.org/1999/xhtml'>" );
        commonWenShu.append( "<head>" );
        commonWenShu.append( "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" );
        commonWenShu.append( "<title>"+title+"</title>" );
        commonWenShu.append( " <link href=\""+contextPath+"/css/word.css\" rel=\"stylesheet\" type=\"text/css\" /> " );
//        commonWenShu.append( "<script> function print_page(){window.print();} " );
//        commonWenShu.append("  window.onload=function() {   ");
//        commonWenShu.append("     if  (navigator.userAgent.indexOf('MSIE') >= 0)  {  ");
//        commonWenShu.append("   }  else  {   ");
//        commonWenShu.append("  document.getElementById('printset').style.visibility='hidden';  " );
//        commonWenShu.append("  document.getElementById('printview').style.visibility='hidden'; } " );
//        commonWenShu.append(" } ");
//        commonWenShu.append( " </script> " );
        commonWenShu.append( "</head>" );
        commonWenShu.append( "<body>" );
//        commonWenShu.append( "<object ID='WebBrowser' WIDTH='0' HEIGHT='0' CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>" );
//        commonWenShu.append( "<center>" );
//        commonWenShu.append( "<div class='right-top'>" );
//        commonWenShu.append( "<button onclick='print_set()' class='printbutton' id='printset'>打印设置</button>" );
//        commonWenShu.append( "<button onclick='printPreview()' class='printbutton' id='printview'>打印预览 </button>" );
//        commonWenShu.append( "<button onclick='print_page()' class='printbutton' id='startprint'>开始打印</button></div>" );
//        commonWenShu.append( "</center>" );
        commonWenShu.append( bodyWenShu );
        commonWenShu.append( "</body>" );
        commonWenShu.append( "</html>" );
        return commonWenShu.toString();
    }

    /**
     * 
     * 《食品经营许可证》申请受理通知书
     * @param list
     * @param fileTitle
     * @return
     */
    public static String getShenQingShouLi(List<Map<String, Object>> list, String fileTitle) {
        StringBuffer wenshu = new StringBuffer();
        String writNo = StringUtil.nullToEmpty(list.get(0).get("WRIT_NO"));

        wenshu.append( "<div class='print_template_tzs' >" );
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第一联由许可机关留存）</p>" );
        wenshu.append( "<p class='t_c'>"+writNo+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>提交的");
        wenshu.append("《食品经营许可证》申请，材料齐全、符合法定形式，我局决定予以受理。</p>" );
        wenshu.append( "<p class='textIndent2'>我局将对您的申请材料进行核实，并于本通知之日起20个工作日内作出是否准予许可的决定。</p>" );
        wenshu.append( "<p class='textR2' style='margin-top:50px;'>（公　章）&nbsp;&nbsp;&nbsp;</p>"  );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textIndent2'>受理审查人员签字：</p>" );
        wenshu.append( "<p class='textIndent2'>申请人或指定代表（委托代理人）签字：</p>" );
        wenshu.append( "<p class='textIndent2'>联系电话：</p>" );
        wenshu.append( "<p class='t_c'>（加盖许可机关印章）</p>" );
        wenshu.append( "<div style='border-bottom:1px dashed #000;overflow:hidden;height:1px;margin:20px 0' ></div>");
    
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第二联由申请人留存）</p>" );
        wenshu.append( "<p class='t_c'>"+writNo+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>提交的");
        wenshu.append("《食品经营许可证》申请，材料齐全、符合法定形式，我局决定予以受理。</p>" );
        wenshu.append( "<p class='textIndent2'>我局将对您的申请材料进行核实，并于本通知之日起20个工作日内作出是否准予许可的决定。</p>" );
        wenshu.append( "<p class='textR2' style='margin-top:50px;'>（公　章）&nbsp;&nbsp;&nbsp;</p>"  );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "</div>" );

        return wenshu.toString();
    }
    /**
     * 证照正本
     * @param fileTitle 
     * @param iSSU_DATE_DAY 
     * @param iSSU_DATE_MONTH 
     * @param iSSU_DATE_YEAR 
     * @param dUTY_MAN 
     * @param iNSPECT_USER 
     * @param vALID_END_DATE_DAY 
     * @param vALID_END_DATE_MONTH 
     * @param vALID_END_DATE_YEAR 
     * @param lIC_NO
     * @param cORP_NAME
     * @param oPER_MAN_NAME
     * @param aDDR
     * @param fARE_PLACE
     * @param mAIN_KIND
     * @param mANAGE_ITEM
     * @return
     */
    public static String getZhenBen( String id, String seq_id, String lIC_NO, String cORP_NAME, String rEG_NO, String oPER_MAN_NAME, String aDDR, String fARE_PLACE, String mAIN_KIND,
            String mANAGE_ITEM, String vALID_END_DATE_YEAR, String vALID_END_DATE_MONTH, String vALID_END_DATE_DAY,String iNSPECT_ORG,String iNSPECT_USER, String dUTY_MAN, String iSSU_DATE_YEAR,
            String iSSU_DATE_MONTH, String iSSU_DATE_DAY, String fileTitle ) {
        StringBuffer ZhenBenwenshu = new StringBuffer();
        ZhenBenwenshu.append(" <!DOCTYPE  html  PUBLIC  '-//W3C//DTD  XHTML  1.0  Transitional//EN'  'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'> ");
        ZhenBenwenshu.append(" <html  xmlns='http://www.w3.org/1999/xhtml'> ");
        ZhenBenwenshu.append(" <head> ");
        ZhenBenwenshu.append(" <meta  http-equiv='Content-Type'  content='text/html;  charset=utf-8'  /> ");
        ZhenBenwenshu.append(" <title>"+fileTitle+"</title> ");
        ZhenBenwenshu.append(" <style type='text/css'> ");
        ZhenBenwenshu.append(" html,  body,  div,  div,  iframe,  H1,  h2,  h3,  h4,  h5,  h6,  p,  blockquote,  pre,  a,  address,  big,  img,  cite,  code,  del,  em,  font,  img,  ins,  small,  strong,  var,  b,  u,  i,  center,  dl,  dt,  dd,  ol,  ul,  div,  fieldset,  form,  label,  legend,  table,  tr,  td,  th,  i,  div  {padding:0px;  margin:0px;  border:0;  } ");
        ZhenBenwenshu.append(" *  {box-sizing:  border-box;  -moz-box-sizing:  border-box;} ");
        ZhenBenwenshu.append(" body  {  font-size:18pt;  color:#000;  font-family:宋体;} ");
        ZhenBenwenshu.append(" div  {  divst-style:none;  } ");
        ZhenBenwenshu.append(" @page  {  size:  A3  landscape;} ");
        ZhenBenwenshu.append(" .jyzmc{position:absolute;left:120mm;top:114mm;} ");
        ZhenBenwenshu.append(" .shxydm{position:absolute;left:120mm;top:128mm;} ");
        ZhenBenwenshu.append(" .fddbr{position:absolute;left:120mm;top:145mm;} ");
        ZhenBenwenshu.append(" .zs{position:absolute;left:120mm;top:159mm;} ");
        ZhenBenwenshu.append(" .jycs{position:absolute;left:120mm;top:172mm;} ");
        ZhenBenwenshu.append(" .ztyt{position:absolute;left:120mm;top:185mm;} ");
        ZhenBenwenshu.append(" .jyxm{position:absolute;left:120mm;top:197mm;width:120mm;} ");
        ZhenBenwenshu.append(" .yxq{position:absolute;left:90mm;top:249mm;} ");
        ZhenBenwenshu.append(" .yxq  span{display:inline-block;} ");
        ZhenBenwenshu.append(" .yxq  .year{width:27mm;text-align:center;} ");
        ZhenBenwenshu.append(" .yxq  .month{margin-left:0mm;width:15mm;text-align:center;} ");
        ZhenBenwenshu.append(" .yxq  .data{margin-left:7mm;width:15mm;text-align:center;} ");
        ZhenBenwenshu.append(" .xkzbh{position:absolute;left:307mm;top:114mm;} ");
        ZhenBenwenshu.append(" .rc_jdgljg{position:absolute;left:307mm;top:128mm;} ");
        ZhenBenwenshu.append(" .rc_jdglry{position:absolute;left:307mm;top:144mm;} ");
        ZhenBenwenshu.append(" .fzjg{position:absolute;left:307mm;top:172mm;} ");
        ZhenBenwenshu.append(" .qfr{position:absolute;left:307mm;top:198mm;} ");
        ZhenBenwenshu.append(" .qf_data{position:absolute;left:288mm;top:214mm;} ");
        ZhenBenwenshu.append(" .qf_data  span{display:inline-block;} ");
        ZhenBenwenshu.append(" .qf_data  .year{width:27mm;text-align:center;} ");
        ZhenBenwenshu.append(" .qf_data  .month{margin-left:0mm;width:15mm;text-align:center;} ");
        ZhenBenwenshu.append(" .qf_data  .data{margin-left:7mm;width:15mm;text-align:center;} ");
        ZhenBenwenshu.append(" .ewm{position:absolute;width:35mm;height:35mm;left:246mm;top:222mm;} ");
        ZhenBenwenshu.append( " .right-top{width:100px;height:40px;position:fixed;-index:999;left:85%;top:40px;} " );
        ZhenBenwenshu.append(" .font16{font-size:16pt} ");
        ZhenBenwenshu.append(" .font14{font-size:14px} ");
        ZhenBenwenshu.append(" </style> ");

        
        ZhenBenwenshu.append( " <script type='text/JavaScript'> " );
        ZhenBenwenshu.append( " function print_set(){document.all.WebBrowser.ExecWB(8,1);} " );
        ZhenBenwenshu.append( " function printPreview(){ " );
        ZhenBenwenshu.append( " var r=parent.document.body.rows; " );
        ZhenBenwenshu.append( " parent.document.body.rows='0,*'; " );
        ZhenBenwenshu.append( " document.all.WebBrowser.ExecWB(7,1);  " );
        ZhenBenwenshu.append( " parent.document.body.rows=r; } " );
        ZhenBenwenshu.append( " function print_page(){window.print();} " );
        ZhenBenwenshu.append("  window.onload=function() {   ");
        ZhenBenwenshu.append("     if  (navigator.userAgent.indexOf('MSIE') >= 0)  {  ");
        ZhenBenwenshu.append("   }  else  {   ");
        ZhenBenwenshu.append("  document.getElementById('printset').style.visibility='hidden';  " );
        ZhenBenwenshu.append("  document.getElementById('printview').style.visibility='hidden'; } " );
        ZhenBenwenshu.append(" } ");
        ZhenBenwenshu.append( " </script> " );
         
        ZhenBenwenshu.append(" <style type='text/css' media=print> ");
        ZhenBenwenshu.append( " .printbutton{visibility:hidden} " );
        ZhenBenwenshu.append(" html,  body,  div,  div,  iframe,  H1,  h2,  h3,  h4,  h5,  h6,  p,  blockquote,  pre,  a,  address,  big,  img,  cite,  code,  del,  em,  font,  img,  ins,  small,  strong,  var,  b,  u,  i,  center,  dl,  dt,  dd,  ol,  ul,  div,  fieldset,  form,  label,  legend,  table,  tr,  td,  th,  i,  div  {padding:0px;  margin:0px;  border:0;  } ");
        ZhenBenwenshu.append(" *  {box-sizing:  border-box;  -moz-box-sizing:  border-box;} ");
        ZhenBenwenshu.append(" body  {  font-size:18pt;  color:#000;  font-family:宋体;} ");
        ZhenBenwenshu.append(" div  {  divst-style:none;  } ");
        ZhenBenwenshu.append(" @page  {  size:  A3  landscape;} ");
        ZhenBenwenshu.append(" .jyzmc{position:absolute;left:120mm;top:114mm;} ");
        ZhenBenwenshu.append(" .shxydm{position:absolute;left:120mm;top:128mm;} ");
        ZhenBenwenshu.append(" .fddbr{position:absolute;left:120mm;top:145mm;} ");
        ZhenBenwenshu.append(" .zs{position:absolute;left:120mm;top:159mm;} ");
        ZhenBenwenshu.append(" .jycs{position:absolute;left:120mm;top:172mm;} ");
        ZhenBenwenshu.append(" .ztyt{position:absolute;left:120mm;top:185mm;} ");
        ZhenBenwenshu.append(" .jyxm{position:absolute;left:120mm;top:197mm;width:120mm;} ");
        ZhenBenwenshu.append(" .yxq{position:absolute;left:90mm;top:249mm;} ");
        ZhenBenwenshu.append(" .yxq  span{display:inline-block;} ");
        ZhenBenwenshu.append(" .yxq  .year{width:27mm;text-align:center;} ");
        ZhenBenwenshu.append(" .yxq  .month{margin-left:0mm;width:15mm;text-align:center;} ");
        ZhenBenwenshu.append(" .yxq  .data{margin-left:7mm;width:15mm;text-align:center;} ");
        ZhenBenwenshu.append(" .xkzbh{position:absolute;left:307mm;top:114mm;} ");
        ZhenBenwenshu.append(" .rc_jdgljg{position:absolute;left:307mm;top:128mm;} ");
        ZhenBenwenshu.append(" .rc_jdglry{position:absolute;left:307mm;top:144mm;} ");
        ZhenBenwenshu.append(" .fzjg{position:absolute;left:307mm;top:172mm;} ");
        ZhenBenwenshu.append(" .qfr{position:absolute;left:307mm;top:198mm;} ");
        ZhenBenwenshu.append(" .qf_data{position:absolute;left:288mm;top:214mm;} ");
        ZhenBenwenshu.append(" .qf_data  span{display:inline-block;} ");
        ZhenBenwenshu.append(" .qf_data  .year{width:27mm;text-align:center;} ");
        ZhenBenwenshu.append(" .qf_data  .month{margin-left:0mm;width:15mm;text-align:center;} ");
        ZhenBenwenshu.append(" .qf_data  .data{margin-left:7mm;width:15mm;text-align:center;} ");
        ZhenBenwenshu.append(" .ewm{position:absolute;width:35mm;height:35mm;left:246mm;top:222mm;} ");
        ZhenBenwenshu.append(" .font16{font-size:16pt} ");
        ZhenBenwenshu.append(" .font14{font-size:14px} ");
        ZhenBenwenshu.append(" </style> ");

        
        ZhenBenwenshu.append(" </head> ");
        ZhenBenwenshu.append(" <body> ");
        ZhenBenwenshu.append( "<object ID='WebBrowser' WIDTH='0' HEIGHT='0' CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>" );
        ZhenBenwenshu.append( "<center>" );
        ZhenBenwenshu.append( "<div class='right-top'>" );
        ZhenBenwenshu.append( "<button onclick='print_set()' class='printbutton' id='printset'>打印设置</button>" );
        ZhenBenwenshu.append( "<button onclick='printPreview()' class='printbutton' id='printview'>打印预览 </button>" );
        ZhenBenwenshu.append( "<button onclick='print_page()' class='printbutton' id='startprint'>开始打印</button></div>" );
        ZhenBenwenshu.append( "</center>" );
        ZhenBenwenshu.append(" <div  class='jyzmc'>"+cORP_NAME+"</div> ");
        ZhenBenwenshu.append(" <div  class='shxydm'>"+rEG_NO+"</div> ");
        ZhenBenwenshu.append(" <div  class='fddbr'>"+oPER_MAN_NAME+"</div> ");
        ZhenBenwenshu.append(" <div  class='zs'>"+aDDR+"</div> ");
        ZhenBenwenshu.append(" <div  class='jycs'>"+fARE_PLACE+"</div> ");
        ZhenBenwenshu.append(" <div  class='ztyt'>"+mAIN_KIND+"</div> ");
        
        if(mANAGE_ITEM.length()<=150) {
            ZhenBenwenshu.append(" <div  class='jyxm'>"+mANAGE_ITEM+"</div> ");
        }else if(mANAGE_ITEM.length()>150&&mANAGE_ITEM.length()<=195) {
            ZhenBenwenshu.append(" <div  class='jyxm font16'>"+mANAGE_ITEM+"</div> ");
        }else if(mANAGE_ITEM.length()>195) {
            ZhenBenwenshu.append(" <div  class='jyxm font14'>"+mANAGE_ITEM+"</div> ");
        }
        ZhenBenwenshu.append(" <div  class='yxq'><span  class='year'>"+vALID_END_DATE_YEAR+"</span><span  class='month'>"+vALID_END_DATE_MONTH+"</span><span  class='data'>"+vALID_END_DATE_DAY+"</span></div> ");
        ZhenBenwenshu.append(" <div  class='xkzbh'>"+lIC_NO+"</div> ");
        ZhenBenwenshu.append(" <div  class='rc_jdgljg'>"+iNSPECT_ORG+"</div> ");
        ZhenBenwenshu.append(" <div  class='rc_jdglry'>"+iNSPECT_USER+"</div> ");
        ZhenBenwenshu.append(" <div  class='fzjg'></div> ");
        ZhenBenwenshu.append(" <div  class='qfr'>"+dUTY_MAN+"</div> ");
        ZhenBenwenshu.append(" <div  class='qf_data'><span  class='year'>"+iSSU_DATE_YEAR+"</span><span  class='month'>"+iSSU_DATE_MONTH+"</span><span  class='data'>"+iSSU_DATE_DAY+"</span></div> ");
        ZhenBenwenshu.append(" <div  class='ewm'><img alt='二维码图片' style='width:35mm;height:35mm;' src='LicenseManageServlet.json?loadQRcode=true&id="+id+"&seq_id="+seq_id+"'/></div> ");
        ZhenBenwenshu.append(" </body> ");
        ZhenBenwenshu.append(" </html> ");
        return ZhenBenwenshu.toString();
    }
    
    /**
     * 《食品经营许可证》申请收到材料凭据存
     * @param list
     * @param fileTitle
     * @return
     */
    public static String getCaiLiaoPingJu(List<Map<String, Object>> list, String fileTitle) {
        StringBuffer wenshu = new StringBuffer();
        wenshu.append( "<div class='print_template_tzs' >" );
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第一联由许可机关留存）</p>" );
        wenshu.append( "<p class='t_c'>"+list.get(0).get("WRIT_NO_OTHER")+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>提交的");
        wenshu.append("《食品经营许可证》申请材料已收到，我局将于5日内作出是否予以受理的决定并告知需要补正的全部内容。</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textIndent2'>受理审查人员签字：</p>" );
        wenshu.append( "<p class='textIndent2'>申请人或指定代表（委托代理人）签字：</p>" );
        wenshu.append( "<p class='textIndent2'>联系电话：</p>" );
        wenshu.append( "<p class='t_c'>（加盖许可机关印章）</p>" );
        wenshu.append( "<div style='border-bottom:1px dashed #000;margin:20px 0;' ></div>");
        
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第二联由申请人留存）</p>" );
        wenshu.append( "<p class='t_c'>"+list.get(0).get("WRIT_NO_OTHER")+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>提交的");
        wenshu.append("《食品经营许可证》申请材料已收到，我局将于5日内作出是否予以受理的决定并告知需要补正的全部内容。</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "</div>" );

        return wenshu.toString();
    }

    /**
     * 食品经营许可不予受理通知书
     * @param list
     * @param fileTitle
     * @return
     */
    public static String getBuYuShouLi(List<Map<String, Object>> list, String fileTitle) {
        StringBuffer wenshu = new StringBuffer();
        wenshu.append( "<div class='print_template_tzs' >" );
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第一联由许可机关留存）</p>" );
        wenshu.append( "<p class='t_c'>"+list.get(0).get("WRIT_NO")+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>经审查，你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>提交的");
        wenshu.append("《食品经营许可证》申请，我局决定不予受理。不予受理理由如下：<span class='w350'><u>&nbsp;"+list.get(0).get("DEAL_OPINION")+"&nbsp;</u>。</span></p>" );
        wenshu.append( "<p class='textIndent2'>对本不予受理决定持有异议的，可以自收到本通知之日起60日内依据《中华人民共和国行政复议法》的规定，申请行政复议，" );
        wenshu.append( "也可以自收到本通知之日起3个月内依据《中华人民共和国行政诉讼法》的规定，直接向人民法院提起行政诉讼。</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textIndent2'>受理审查人员签字：</p>" );
        wenshu.append( "<p class='textIndent2'>申请人或指定代表（委托代理人）签字：</p>" );
        wenshu.append( "<p class='textIndent2'>联系电话：</p>" );
        wenshu.append( "<p class='t_c'>（加盖许可机关印章）</p>" );
        wenshu.append( "<div style='border-bottom:1px dashed #000;margin:20px 0;' ></div>");
        
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第二联由申请人留存）</p>" );
        wenshu.append( "<p class='t_c'>"+list.get(0).get("WRIT_NO")+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>经审查，你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>提交的");
        wenshu.append("《食品经营许可证》申请，我局决定不予受理。不予受理理由如下：<span class='w350'><u>&nbsp;"+list.get(0).get("DEAL_OPINION")+"&nbsp;</u>。</span></p>" );
        wenshu.append( "<p class='textIndent2'>对本不予受理决定持有异议的，可以自收到本通知之日起60日内依据《中华人民共和国行政复议法》的规定，申请行政复议，" );
        wenshu.append( "也可以自收到本通知之日起3个月内依据《中华人民共和国行政诉讼法》的规定，直接向人民法院提起行政诉讼。</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "</div>" );
        return wenshu.toString();
    }

    /**
     * 食品经营许可补证材料通知书
     * @param list
     * @param fileTitle
     * @return
     */
    public static String getBuZhengCaiLiao(List<Map<String, Object>> list, String fileTitle) {
        StringBuffer wenshu = new StringBuffer();
        wenshu.append( "<div class='print_template_tzs' >" );
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第一联由许可机关留存）</p>" );
        wenshu.append( "<p class='t_c'>"+list.get(0).get("WRIT_NO")+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>你单位有关食品经营许可的申请，本机关已收悉。经审查，下列申请材料：");
        wenshu.append( "<span class='w350'><u>&nbsp;"+list.get(0).get("DEAL_OPINION")+"&nbsp;</u></span>" );
        wenshu.append( "不齐全或不符合法定形式，请5日内予以补正，逾期不补正，视作放弃申请。</p>" );
        wenshu.append( "<p class='textIndent2'>签收:</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='t_c'>（加盖许可机关印章）</p>" );
        wenshu.append( "<div style='border-bottom:1px dashed #000;margin:20px 0;' ></div>");
        
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第二联由申请人留存）</p>" );
        wenshu.append( "<p class='t_c'>"+list.get(0).get("WRIT_NO")+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>你单位有关食品经营许可的申请，本机关已收悉。经审查，下列申请材料：");
        wenshu.append( "<span class='w350'><u>&nbsp;"+list.get(0).get("DEAL_OPINION")+"&nbsp;</u></span>" );
        wenshu.append( "不齐全或不符合法定形式，请5日内予以补正，逾期不补正，视作放弃申请。</p>" );
        wenshu.append( "<p class='textIndent2'>签收:</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "</div>" ); 
        return wenshu.toString();
    }

    /**
     * 听证记录表
     * @param fileTitle
     * @return
     */
    public static String getTingZhengJiLuBiao(String fileTitle) {
        StringBuffer HFDJBwenshu = new StringBuffer();

        HFDJBwenshu.append( "<div class='print_template_djb'>" );
        HFDJBwenshu.append( "<h1 class='djb_title1'>"+fileTitle+"</h1>" );
        HFDJBwenshu.append( "<div class='djb_table'><table> <tr ><th>听证开始时间</th><td></td><th>听证结束时间</th><td></td></tr>" );
        HFDJBwenshu.append( "<tr > <th>听证地点</th> <td  colspan='3'></td></tr>" );
        HFDJBwenshu.append( "<tr ><th>听证主持人</th><td></td><th>记录人</th><td></td></tr>" );
        HFDJBwenshu.append( "<tr ><th>翻译</th><td></td><th>其他参加人</th><td></td></tr>" );
        HFDJBwenshu.append( "<tr height='400px'> <th >听证结果摘要</th><td colspan='3'></td></tr>" );
        HFDJBwenshu.append( "<tr height='150px'> <th>听证结论</th><td colspan='3'></td></tr></table></div>" );
        HFDJBwenshu.append( "</div>" );
  
        return HFDJBwenshu.toString();
    }

    /**
     * 《食品经营许可证》申请准予通知书
     * @param list
     * @param fileTitle
     * @return
     */
    public static String getShenQingZhunYu(List<Map<String, Object>> list, String fileTitle) {
        StringBuffer wenshu = new StringBuffer();
        String writNo = StringUtil.nullToEmpty(list.get(0).get("WRIT_NO"));
        wenshu.append( "<div class='print_template_tzs'>" );
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第一联由许可机关留存）</p>" );
        wenshu.append( "<p class='t_c'>"+writNo+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>");
        wenshu.append( "提交的许可申请，经审查，我局作出准予许可的决定。</p>" );
        wenshu.append( "<p class='textIndent2'>请申请人或其指定代表（委托代理人）于<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>年" );
        wenshu.append( "<u>&nbsp;&nbsp;</u>月<u>&nbsp;&nbsp;</u>日,凭本通知书及本人身份证件到我局领取《食品经营许可证》。</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textIndent2'>审核人员签字：</p>" );
        wenshu.append( "<p class='textIndent2'>申请人或指定代表（委托代理人）签字：</p>" );
        wenshu.append( "<p class='textIndent2'>联系电话：</p>" );
        wenshu.append( "<p class='textC'>（加盖许可机关印章）</p>" );
        wenshu.append( "<div style='border-bottom:1px dashed #000;overflow:hidden;height:1px;margin:20px 0' /></div>");

        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第二联由申请人留存）</p>" );
        wenshu.append( "<p class='t_c'>"+writNo+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>");
        wenshu.append( "提交的许可申请，经审查，我局作出准予许可的决定。</p>" );
        wenshu.append( "<p class='textIndent2'>请申请人或其指定代表（委托代理人）于<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>年" );
        wenshu.append( "<u>&nbsp;&nbsp;</u>月<u>&nbsp;&nbsp;</u>日,凭本通知书及本人身份证件到我局领取《食品经营许可证》。</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "</div>" );
        return wenshu.toString();
    }

    /**
     * 《食品经营许可证》申请驳回通知书
     * @param list
     * @param fileTitle
     * @return
     */
    public static String getShenQingBoHui(List<Map<String, Object>> list, String fileTitle) {
        StringBuffer wenshu = new StringBuffer();
        String writNo = StringUtil.nullToEmpty(list.get(0).get("WRIT_NO"));

        wenshu.append( "<div class='print_template_tzs'>" );
        wenshu.append( "<h1 class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第一联由许可机关留存）</p>" );
        wenshu.append( "<p class='t_c'>"+writNo+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>经审查，你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>");
        wenshu.append( "提交的《食品经营许可证》申请，我局决定不予许可。不予许可理由如下：<span class='w350'><u>&nbsp;"+list.get(0).get("DEAL_OPINION")+"&nbsp;</u>。</span></p>" );
        wenshu.append( "<p class='textIndent2'>对本不予许可决定持有异议的，可以自收到本通知之日起60日内依据《中华人民共和国行政复议法》的规定，申请行政复议，" );
        wenshu.append( "也可以自收到本通知之日起3个月内依据《中华人民共和国行政诉讼法》的规定，直接向人民法院提起行政诉讼。</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textIndent2'>审核人员签字：</p>" );
        wenshu.append( "<p class='textIndent2'>申请人或指定代表（委托代理人）签字：</p>" );
        wenshu.append( "<p class='textIndent2'>联系电话：</p>" );
        wenshu.append( "<p class='textC'>（加盖许可机关印章）</p>" );
        wenshu.append( "<div style='border-bottom:1px dashed #000;overflow:hidden;height:1px;margin:20px 0' /></div>");
       
        wenshu.append( "<h1  class='tzs_title1'>"+fileTitle+"</h1>" );
        wenshu.append( "<p class='t_c'>（第二联由申请人留存）</p>" );
        wenshu.append( "<p class='t_c'>"+writNo+"</p>" );
        wenshu.append( "<p><span class='w350'><u>"+list.get(0).get("CORP_NAME")+"</u></span>：</p>" );
        wenshu.append( "<p class='textIndent2'>经审查，你（单位）于<span class='w350'>"+list.get(0).get("APPLY_DATE")+"</span>");
        wenshu.append( "提交的《食品经营许可证》申请，我局决定不予许可。不予许可理由如下：<span class='w350'><u>&nbsp;"+list.get(0).get("DEAL_OPINION")+"&nbsp;</u>。</span></p>" );
        wenshu.append( "<p class='textIndent2'>对本不予许可决定持有异议的，可以自收到本通知之日起60日内依据《中华人民共和国行政复议法》的规定，申请行政复议，" );
        wenshu.append( "也可以自收到本通知之日起3个月内依据《中华人民共和国行政诉讼法》的规定，直接向人民法院提起行政诉讼。</p>" );
        wenshu.append( "<p class='textR2'>（公　章）&nbsp;&nbsp;&nbsp;</p>" );
        wenshu.append( "<p class='textR2'>"+list.get(0).get("DEAL_DATE")+"&nbsp;&nbsp;</p>" );
        wenshu.append( "</div>" );

        return wenshu.toString(); 
    }

    /**
     * 《食品经营许可证》申请审核意见表
     * @param shenpilist
     * @param opinionList
     * @param fileTitle
     * @return
     */
    public static String getShenHeYiJian(List<Map<String, Object>> shenpilist, Map<String, Object> opinionMap, String fileTitle) {
        StringBuffer HFDJBwenshu = new StringBuffer();

        HFDJBwenshu.append( "<div class='print_template_sq'>" );
        HFDJBwenshu.append( "<h1 class='sq_title1'>"+fileTitle+"</h1>" );
        HFDJBwenshu.append( "<div class='sq_table2'><table style='border-bottom:0;'> <tr ><th  class='w120'>经营者名称</th><td>"+shenpilist.get(0).get("CORP_NAME")+"</td><th class='w120'><p class='t_c'>法定代表人<br/>（负责人）</p></th><td>"+shenpilist.get(0).get("OPER_MAN_NAME")+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th><p>社会信用代码<br/>（证件号码）</p></th> <td  colspan='3'>"+shenpilist.get(0).get("REG_NO")+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th>住&nbsp;&nbsp;&nbsp;&nbsp;所</th> <td  colspan='3'>"+shenpilist.get(0).get("ADDR")+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th>经营场所</th> <td  colspan='3'>"+shenpilist.get(0).get("FARE_PLACE")+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th>仓库地址</th> <td  colspan='3'>"+StringUtil.nullToEmpty(shenpilist.get(0).get("STORAGE_PLACE"))+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th>主体业态</th> <td  colspan='3'>"+shenpilist.get(0).get("MAIN_KIND_NAME")+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th>经营项目</th> <td  colspan='3'><p>"+shenpilist.get(0).get("MANAGE_ITEM_NAME")+"</p></td></tr>" );
        HFDJBwenshu.append( "<tr ><th>是否举行听证</th><td>"+opinionMap.get("IFHEARING")+"</td><th>听证举行日期</th><td>"+StringUtil.nullToEmpty(opinionMap.get("HEAR_DATE"))+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th>听证结论</th> <td  colspan='3'>"+StringUtil.nullToEmpty(opinionMap.get("HEAR_RESULT"))+"</td></tr>" );
        HFDJBwenshu.append( "<tr ><th>是否现场核查</th><td>"+StringUtil.nullToEmpty(opinionMap.get("IFCHECK"))+"</td><th>现场核查日期</th><td>"+StringUtil.nullToEmpty(opinionMap.get("CHECK_DATE"))+"</td></tr>" );
        HFDJBwenshu.append( "<tr ><th><p>现场核查<br/>负责人</p></th><td>"+StringUtil.nullToEmpty(opinionMap.get("CHIEF_NAME"))+"</td><th>核查结论</th><td>"+StringUtil.nullToEmpty(opinionMap.get("RESULT"))+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th >受理意见</th><td colspan='3'>"+StringUtil.nullToEmpty(opinionMap.get("SHOULI_DEAL_OPINION"))+"<div>&nbsp;&nbsp;&nbsp;受理人员签字：" );
        HFDJBwenshu.append( "　　　　　　年　　月　　日</div></td></tr>" ); 
        HFDJBwenshu.append( "<tr > <th >审查意见</th><td colspan='3'>"+StringUtil.nullToEmpty(opinionMap.get("SHENCHA_DEAL_OPINION"))+"<div>&nbsp;&nbsp;&nbsp;审查人员签字：" );
        HFDJBwenshu.append( "　　　　　　年　　月　　日</div></td></tr></table>" );
        HFDJBwenshu.append( "<table style='border-top:0;'><tr ><th  style='border-top:0;vertical-align:middle;width:120px'>核准意见</th>" ); 
        HFDJBwenshu.append( "<td style='border-top:0;width:40%;text-align:right;'><div style='text-align:left'><p>"+StringUtil.nullToEmpty(opinionMap.get("SHENHE_DEAL_OPINION"))+"</p></div><div style='text-align:left'>审核人员签字：</div>　　　　年　　月　　日</td>" );
        HFDJBwenshu.append( "<td style='border-top:0;width:40%;text-align:right;'><div style='text-align:left'><p>"+StringUtil.nullToEmpty(opinionMap.get("SHENPI_DEAL_OPINION"))+"</p></div><div style='text-align:left'>审批人员签字：</div>　　　　年　　月　　日</td></tr>");
        HFDJBwenshu.append( "<tr > <th><p>日常监管<br/>责任人</p></th><td colspan='2'>"+shenpilist.get(0).get("INSPECT_USER")+"</td></tr>" );
        HFDJBwenshu.append( "<tr > <th><p>日常监督<br/>管理机构</p></th><td colspan='2'>"+shenpilist.get(0).get("INSPECT_ORG")+"</td></tr>" );
        HFDJBwenshu.append( "<tr height='100px'> <th>备&nbsp;&nbsp;&nbsp;&nbsp;注</th><td colspan='2'></td></tr>" );
        HFDJBwenshu.append( "</table></div></div>" );
 
        return HFDJBwenshu.toString();
    }


    
    /**
     * 证照副本
     * @param fileTitle 
     * @param lIC_NO
     * @param cORP_NAME
     * @param oPER_MAN_NAME
     * @param aDDR
     * @param fARE_PLACE
     * @param mAIN_KIND
     * @param mANAGE_ITEM ...
     * @return
     */
    public static String getFuBen( String id, String seq_id, String lIC_NO, String cORP_NAME, String rEG_NO, String oPER_MAN_NAME, String aDDR, String fARE_PLACE_OTHER, String mAIN_KIND,
            String mANAGE_ITEM, String vALID_END_DATE_YEAR, String vALID_END_DATE_MONTH, String vALID_END_DATE_DAY,String iNSPECT_ORG, String iNSPECT_USER, String dUTY_MAN, String iSSU_DATE_YEAR,
            String iSSU_DATE_MONTH, String iSSU_DATE_DAY, String fileTitle ) {
        StringBuffer FuBenwenshu = new StringBuffer();
        FuBenwenshu.append(" <div class='page_wrap'><div  class='jyzmc'>"+cORP_NAME+"</div> ");
        FuBenwenshu.append(" <div  class='shxydm'>"+rEG_NO+"</div> ");
        FuBenwenshu.append(" <div  class='fddbr'>"+oPER_MAN_NAME+"</div> ");
        FuBenwenshu.append(" <div  class='zs'>"+aDDR+"</div> ");
        FuBenwenshu.append(" <div  class='jycs'>"+fARE_PLACE_OTHER+"</div> ");
        FuBenwenshu.append(" <div  class='ztyt'>"+mAIN_KIND+"</div> ");
        if(mANAGE_ITEM.length()<=150) {
            FuBenwenshu.append(" <div  class='jyxm'>"+mANAGE_ITEM+"</div> ");
        }else if(mANAGE_ITEM.length()>150&&mANAGE_ITEM.length()<=195) {
            FuBenwenshu.append(" <div  class='jyxm font10'>"+mANAGE_ITEM+"</div> ");
        }else if(mANAGE_ITEM.length()>195) {
            FuBenwenshu.append(" <div  class='jyxm font9'>"+mANAGE_ITEM+"</div> ");
        }
        
        FuBenwenshu.append(" <div  class='yxq'><span  class='year'>"+vALID_END_DATE_YEAR+"</span><span  class='month'>"+vALID_END_DATE_MONTH+"</span><span  class='data'>"+vALID_END_DATE_DAY+"</span></div> ");
        FuBenwenshu.append(" <div  class='xkzbh'>"+lIC_NO+"</div> ");
        FuBenwenshu.append(" <div  class='rc_jdgljg'>"+iNSPECT_ORG+"</div> ");
        FuBenwenshu.append(" <div  class='rc_jdglry'>"+iNSPECT_USER+"</div> ");
        FuBenwenshu.append(" <div  class='fzjg'></div> ");
        FuBenwenshu.append(" <div  class='qfr'>"+dUTY_MAN+"</div> ");
        FuBenwenshu.append(" <div  class='qf_data'><span  class='year'>"+iSSU_DATE_YEAR+"</span><span  class='month'>"+iSSU_DATE_MONTH+"</span><span  class='data'>"+iSSU_DATE_DAY+"</span></div> ");
        FuBenwenshu.append(" <div  class='ewm'><img alt='二维码图片' style='width:35mm;height:35mm;' id='pic' src='LicenseManageServlet.json?loadQRcode=true&id="+id+"&seq_id="+seq_id+"'/></div> ");
        FuBenwenshu.append("</div> " );
        return FuBenwenshu.toString();
    }
    
    /**
     * 副本外层
     * @param bodyWenShu
     * @param title
     * @return
     */
    public static String getCommonFuBen( String bodyWenShu, String title ) {
        StringBuffer CommonFuBenwenshu = new StringBuffer();
        CommonFuBenwenshu.append(" <!DOCTYPE  html  PUBLIC  '-//W3C//DTD  XHTML  1.0  Transitional//EN'  'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'> ");
        CommonFuBenwenshu.append(" <html  xmlns='http://www.w3.org/1999/xhtml'> ");
        CommonFuBenwenshu.append(" <head> ");
        CommonFuBenwenshu.append(" <meta  http-equiv='Content-Type'  content='text/html;  charset=utf-8'  /> ");
        CommonFuBenwenshu.append(" <title>"+title+"</title> ");
        CommonFuBenwenshu.append(" <style type='text/css'> ");
        CommonFuBenwenshu.append(" html,  body,  div,  div,  iframe, object, center,H1,  h2,  h3,  h4,  h5,  h6,  p,  blockquote,  pre,  a,  address,  big,  img,  cite,  code,  del,  em,  font,  img,  ins,  small,  strong,  var,  b,  u,  i,  center,  dl,  dt,  dd,  ol,  ul,  li,  fieldset,  form,  label,  legend,  table,  tr,  td,  th,  i,  div  {padding:0px;  margin:0px;  border:0;} ");
        CommonFuBenwenshu.append(" *{box-sizing:  border-box;  -moz-box-sizing:  border-box;} ");
        CommonFuBenwenshu.append(" body  {  font-size:12pt;  color:#000;  font-family:宋体;  } ");
        CommonFuBenwenshu.append(" @page  {size:  A4  landscape;  } ");
        CommonFuBenwenshu.append(" .page_wrap{width:auto;height:19cm;position:relative;margin:0;} ");
        

        
        CommonFuBenwenshu.append(" .jyzmc{position:absolute;left:76mm;top:46mm;} ");
        CommonFuBenwenshu.append(" .shxydm{position:absolute;left:76mm;top:55mm;} ");
        CommonFuBenwenshu.append(" .fddbr{position:absolute;left:76mm;top:70mm;} ");
        CommonFuBenwenshu.append(" .zs{position:absolute;left:76mm;top:80mm;width:75mm;} ");
        CommonFuBenwenshu.append(" .jycs{position:absolute;left:76mm;top:90mm;width:75mm;} ");
        CommonFuBenwenshu.append(" .ztyt{position:absolute;left:76mm;top:110mm;width:75mm;} ");
        CommonFuBenwenshu.append(" .jyxm{position:absolute;left:76mm;top:120mm;width:75mm;} ");
        CommonFuBenwenshu.append(" .yxq{position:absolute;left:53mm;top:170mm;} ");
        CommonFuBenwenshu.append(" .yxq  span{display:inline-block;} ");
        CommonFuBenwenshu.append(" .yxq  .year{width:16mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .yxq  .month{margin-left:6mm;width:9mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .yxq  .data{margin-left:6mm;width:9mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .xkzbh{position:absolute;left:200mm;top:90mm;} ");
        CommonFuBenwenshu.append(" .rc_jdgljg{position:absolute;left:200mm;top:98mm;} ");
        CommonFuBenwenshu.append(" .rc_jdglry{position:absolute;left:200mm;top:106mm;} ");
        CommonFuBenwenshu.append(" .fzjg{position:absolute;left:200mm;top:121mm;} ");
        CommonFuBenwenshu.append(" .qfr{position:absolute;left:200mm;top:136mm;} ");
        CommonFuBenwenshu.append(" .qf_data{position:absolute;left:195mm;top:148mm;} ");
        CommonFuBenwenshu.append(" .qf_data  span{display:inline-block;} ");
        CommonFuBenwenshu.append(" .qf_data  .year{width:16mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .qf_data  .month{margin-left:4mm;width:9mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .qf_data  .data{margin-left:6mm;width:9mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .ewm{position:absolute;width:35mm;height:35mm;left:158mm;top:143mm;} "); 
        CommonFuBenwenshu.append( " .right-top{width:100px;height:40px;position:fixed;-index:999;left:85%;top:40px;} " );
        CommonFuBenwenshu.append(" .font10{font-size:10pt} ");
        CommonFuBenwenshu.append(" .font9{font-size:9px} ");
        CommonFuBenwenshu.append(" </style> ");
        
        CommonFuBenwenshu.append( " <script type='text/JavaScript'> " );
        CommonFuBenwenshu.append( " function print_set(){document.all.WebBrowser.ExecWB(8,1);} " );
        CommonFuBenwenshu.append( " function printPreview(){ " );
        CommonFuBenwenshu.append( " var r=parent.document.body.rows; " );
        CommonFuBenwenshu.append( " parent.document.body.rows='0,*'; " );
        CommonFuBenwenshu.append( " document.all.WebBrowser.ExecWB(7,1);  " );
        CommonFuBenwenshu.append( " parent.document.body.rows=r; } " );
        CommonFuBenwenshu.append( " function print_page(){window.print();} " );
        CommonFuBenwenshu.append("  window.onload=function() {   ");
        CommonFuBenwenshu.append("     if  (navigator.userAgent.indexOf('MSIE') >= 0)  {  ");
        CommonFuBenwenshu.append("   }  else  {   ");
        CommonFuBenwenshu.append("  document.getElementById('printset').style.visibility='hidden';  " );
        CommonFuBenwenshu.append("  document.getElementById('printview').style.visibility='hidden'; } " );
        CommonFuBenwenshu.append(" } ");
        CommonFuBenwenshu.append( " </script> " );
        
        CommonFuBenwenshu.append(" <style type='text/css' media=print> ");
        CommonFuBenwenshu.append(" .printbutton{visibility:hidden} " );
        CommonFuBenwenshu.append(" html,  body,  div,  div,  iframe,  object, center, H1,  h2,  h3,  h4,  h5,  h6,  p,  blockquote,  pre,  a,  address,  big,  img,  cite,  code,  del,  em,  font,  img,  ins,  small,  strong,  var,  b,  u,  i,  center,  dl,  dt,  dd,  ol,  ul,  li,  fieldset,  form,  label,  legend,  table,  tr,  td,  th,  i,  div  {padding:0px;  margin:0px;  border:0;} ");
        CommonFuBenwenshu.append(" *{box-sizing:  border-box;  -moz-box-sizing:  border-box;} ");
        CommonFuBenwenshu.append(" body  {  font-size:12pt;  color:#000;  font-family:宋体;  } ");
        CommonFuBenwenshu.append(" @page  {size:  A4  landscape;  } ");
        CommonFuBenwenshu.append(" .jyzmc{position:absolute;left:76mm;top:49mm;} ");
        CommonFuBenwenshu.append(" .shxydm{position:absolute;left:76mm;top:58mm;} ");
        CommonFuBenwenshu.append(" .fddbr{position:absolute;left:76mm;top:73mm;} ");
        CommonFuBenwenshu.append(" .zs{position:absolute;left:76mm;top:83mm;width:75mm;} ");
        CommonFuBenwenshu.append(" .jycs{position:absolute;left:76mm;top:93mm;width:75mm;} ");
        CommonFuBenwenshu.append(" .ztyt{position:absolute;left:76mm;top:113mm;width:75mm;} ");
        CommonFuBenwenshu.append(" .jyxm{position:absolute;left:76mm;top:123mm;width:75mm;} ");
        CommonFuBenwenshu.append(" .yxq{position:absolute;left:53mm;top:175mm;} ");
        CommonFuBenwenshu.append(" .yxq  span{display:inline-block;} ");
        CommonFuBenwenshu.append(" .yxq  .year{width:16mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .yxq  .month{margin-left:6mm;width:9mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .yxq  .data{margin-left:4mm;width:9mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .xkzbh{position:absolute;left:200mm;top:93mm;} ");
        CommonFuBenwenshu.append(" .rc_jdgljg{position:absolute;left:200mm;top:101mm;} ");
        CommonFuBenwenshu.append(" .rc_jdglry{position:absolute;left:200mm;top:109mm;} ");
        CommonFuBenwenshu.append(" .fzjg{position:absolute;left:200mm;top:124mm;} ");
        CommonFuBenwenshu.append(" .qfr{position:absolute;left:199mm;top:139mm;} ");
        CommonFuBenwenshu.append(" .qf_data{position:absolute;left:195mm;top:151mm;} ");
        CommonFuBenwenshu.append(" .qf_data  span{display:inline-block;} ");
        CommonFuBenwenshu.append(" .qf_data  .year{width:16mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .qf_data  .month{margin-left:4mm;width:9mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .qf_data  .data{margin-left:6mm;width:9mm;text-align:center;} ");
        CommonFuBenwenshu.append(" .ewm{position:absolute;width:35mm;height:35mm;left:154mm;top:147mm;} "); 
        CommonFuBenwenshu.append( " .right-top{width:100px;height:40px;position:fixed;-index:999;left:85%;top:40px;} " );
        CommonFuBenwenshu.append( ".page_wrap{width:auto;height:auto;}");
        CommonFuBenwenshu.append( ".page_wrap{page-break-after:always;}");
        CommonFuBenwenshu.append( "object,center{display:none}");
        CommonFuBenwenshu.append(" .font10{font-size:10pt} ");
        CommonFuBenwenshu.append(" .font9{font-size:9px} ");
        CommonFuBenwenshu.append(" </style> ");
        
        CommonFuBenwenshu.append(" </head> ");
        CommonFuBenwenshu.append(" <body> ");
        CommonFuBenwenshu.append( "<object ID='WebBrowser' WIDTH='0' HEIGHT='0' CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>" );
        CommonFuBenwenshu.append( "<center>" );
        CommonFuBenwenshu.append( "<div class='right-top'>" );
        CommonFuBenwenshu.append( "<button onclick='print_set()' class='printbutton' id='printset'>打印设置</button>" );
        CommonFuBenwenshu.append( "<button onclick='printPreview()' class='printbutton' id='printview'>打印预览 </button>" );
        CommonFuBenwenshu.append( "<button onclick='print_page()' class='printbutton' id='startprint'>开始打印</button></div>" );
        CommonFuBenwenshu.append(" </div> ");
        CommonFuBenwenshu.append("</center>" );
        CommonFuBenwenshu.append( bodyWenShu );
        CommonFuBenwenshu.append(" </body> ");
        CommonFuBenwenshu.append(" </html> ");

        return CommonFuBenwenshu.toString();
    }

    /**
     * 食品经营许可现场核查（自查）表；
     * @param basicList  主表信息
     * @param itemsList  核查项
     * @param fileTitle  文书名称
     * @return
     */
    public static String getXianChangHeCha(List<Map<String, Object>> basicList, List<Map<String, Object>> itemsList, String fileTitle) {
        StringBuffer GuiDangwenshu = new StringBuffer();
        GuiDangwenshu.append( "<div  class='print_template_djb'>" );
        GuiDangwenshu.append( "<h1 class='djb_title1'>"+fileTitle+"</h1>" );
        GuiDangwenshu.append( "<div class='djb_table2'><table  style='border-bottom:0px;'><tr><td >被核查单位："+basicList.get(0).get("CORP_NAME")+"</td></tr>" );
        GuiDangwenshu.append( "<tr><td >经营场所："+basicList.get(0).get("FARE_PLACE")+"</td></tr>" );
        GuiDangwenshu.append( "<tr><td >申请事项："+basicList.get(0).get("MANAGE_ITEM_NAME")+"</td></tr></table>" );
        GuiDangwenshu.append( "<table style='table-layout: auto;border-bottom:0px;border-top:0px;'>" );
        GuiDangwenshu.append( "<tr><td rowspan='2'style='border-top:0;width:50px;text-align:center;'>序号</td>" );
        GuiDangwenshu.append( "<th style='width:500px;border-top:0' rowspan='2'>核查内容</th><td colspan='2' style='border-top:0;text-align:center;'><p  class='t_c'>自查结果</p></td>" );
        GuiDangwenshu.append( "<td  colspan='2' style='border-top:0;text-align:center;'><p  class='t_c'>核查结果</p></td></tr>" );
        GuiDangwenshu.append( "<tr><td class='t_c'>是</td><td  class='t_c'>否</td><td  class='t_c'>是</td><td  class='t_c'>否</td></tr>" );
        if(itemsList.size()>0){
           for(int i = 0;i<itemsList.size();i++){
               Map<String,Object> itemsMap = itemsList.get(i);
            GuiDangwenshu.append( "<tr><td style='text-align:center;'>"+(i+1)+"</td><td ><p>"+itemsMap.get("ITEM_NAME")+"</p></td><td></td><td ></td><td></td><td ></td></tr>" );
           }
        }
        GuiDangwenshu.append( "</table>" );
//          if(listFile.size()>0) {
//                    for(int i=0;i<listFile.size();i++) {
//                        GuiDangwenshu.append( "<tr><th>"+listFile.get( i ).get( "XH" )+"</th><th>"+listFile.get( i ).get( "FILE_NAME" )+"</th><th>"+listFile.get( i ).get( "PAGE_NUM" )+"</th></tr>" );
//                    }
//          }else {
//              GuiDangwenshu.append( "<tr height='100px'><th colspan='3'>（没有需要归档的材料）</th></tr>" );
//          }
        GuiDangwenshu.append( "<table style='border-bottom:0px;border-top:0px;'><tr><th style='border-top:0;vertical-align:middle;width:120px;height:80px;'><p class='t_c'>现场核查<br/>不合格项</p></th><td style='border-top:0;'></td></tr><tr><th style='vertical-align:middle;height:80px;'>现场核查意见</th><td></td></tr></table>" );
        GuiDangwenshu.append( "<table  style='border-top:0px;'><tr><td style='border-top:0; width:48%;text-align:right;'><div style='text-align:left'>被查单位签字：</div>年　　月　　日</td>" );
        GuiDangwenshu.append( "<td style='border-top:0;width:48%;text-align:right;'><div  style='text-align:left'>被查单位签字：</div>年　　月　　日</td></tr></table></div>" );
        GuiDangwenshu.append( "</div>" ); 
        return GuiDangwenshu.toString();
    }

 

   
}
