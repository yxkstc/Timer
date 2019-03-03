package com.company;
import org.xml.sax.helpers.AttributesImpl;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.dom4j.DocumentHelper;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Configure {
    /*
     * Properties类中的setProtertity()方法
     */
    public  void setGetProperties(String renwuName1, String shijian1, String zhixingtime1, String renwuluj1, String renwuName2, String shijian2, String zhixingtime2, String renwuluj2,String renwuName3, String shijian3, String zhixingtime3, String renwuluj3) throws IOException {
        Properties p = new Properties( );
        FileWriter fw;
        //任务1
        p.setProperty("renwuName1", renwuName1);//设定Properties类中存放的键值对
        p.setProperty("shijian1", shijian1);
        p.setProperty("zhixingtime1", zhixingtime1);
        p.setProperty("renwuluj1", renwuluj1);
        //任务2
        p.setProperty("renwuName2", renwuName2);//设定Properties类中存放的键值对
        p.setProperty("shijian2", shijian2);
        p.setProperty("zhixingtime2", zhixingtime2);
        p.setProperty("renwuluj2", renwuluj2);
        //任务3
        p.setProperty("renwuName3", renwuName3);//设定Properties类中存放的键值对
        p.setProperty("shijian3", shijian3);
        p.setProperty("zhixingtime3", zhixingtime3);
        p.setProperty("renwuluj3", renwuluj3);

        fw = new FileWriter("lib/peizhiwenjian.properties");
        p.store(fw, "Set task name, task time, task execution time, task execution program path. Set up a maximum of three tasks and perform only one task at a time.");//将Properties中的信息存储到文件中
    }

    /*
     * Properties类中的getProtertity()
     */
    public  Properties getGetProperties() throws IOException {
        Properties p = new Properties( );
        FileReader fr;
        fr = new FileReader("lib/peizhiwenjian.properties");
        p.load(fr);//load()方法可通过字符流直接加载文件
        return p;
    }
    /**
     * SAX方式生成xml文档(未使用)
     */
    public void SAXCreateXML(){

        try {
            //生成XML
            //1.创建一个TransformerFactory类的对象
            SAXTransformerFactory tff=(SAXTransformerFactory) SAXTransformerFactory.newInstance();
            //2.通过SAXTransformerFactory对象创建一个TransformerHandler对象
            TransformerHandler handler=tff.newTransformerHandler();
            //3.通过handler对象创建一个Transformer
            Transformer tr=handler.getTransformer();
            //4.通过Transformer对象对生成的xml文件进行设置
            //设置xml的编码
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            //设置xml的“是否换行”
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            //5.创建一个Result对象
            File file=new File("lib/test.xml");
       /* if (!file.exists()) {
            file.createNewFile();
        }*/
            //6.创建Result对象，并且使其与handler关联
            Result result=new StreamResult(new FileOutputStream(file));
            handler.setResult(result);
            //7.利用handler对象进行xml文件内容的编写
            //打开document
            handler.startDocument();
            AttributesImpl attr=new AttributesImpl();
            handler.startElement("", "", "bookstore", attr);
            attr.clear();
            attr.addAttribute("", "", "id", "", "1");
            handler.startElement("", "", "database", attr);
            attr.clear();
            handler.startElement("", "", "name", attr);
            handler.characters("小王子2".toCharArray(), 0, "小王子2".length());
            handler.endElement("", "", "name");
            handler.startElement("", "", "shifou", attr);
            handler.characters("否".toCharArray(), 0, "否".length());
            handler.endElement("", "", "shifou");
            handler.endElement("", "", "database");
            handler.endElement("", "", "bookstore");
            // 关闭document
            handler.endDocument();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * 将表格的数据模型保存到xml文件当中
     *
     * @param model
     * @param file
     */
    public void saveModel(TableModel model, String file) {
        Document doc = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement("table");
        doc.setRootElement(root);
        Element columns = DocumentHelper.createElement("columns");
        for (int i = 0; i < model.getColumnCount(); i++) {
            Element column = DocumentHelper.createElement("column");
            column.addAttribute("name", model.getColumnName(i));
            column.addAttribute("type", model.getColumnClass(i).getName());
            columns.add(column);
        }
        root.add(columns);
        for (int i = 0; i < model.getRowCount(); i++) {
            Element tr = DocumentHelper.createElement("tr");
            for (int j = 0; j < model.getColumnCount(); j++) {
                Element td = DocumentHelper.createElement("td");
                td.setText(model.getValueAt(i, j).toString());
                tr.add(td);
            }
            root.add(tr);
        }
        saveDocument(doc, file);
    }

    /**
     * 保存xml Dom到指定的文件当中
     *
     * @param doc
     * @param name
     */
    public void saveDocument(Document doc, String name) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        try {
            XMLWriter writer = new XMLWriter(format);
            FileOutputStream fos = new FileOutputStream(name);
            writer.setOutputStream(fos);
            writer.write(doc);
            fos.close();
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析指定的xml文件，生成表格数据模型
     *
     * @param name
     *            xml文件路径名称
     * @return XmlTableModel
     */
    public XmlTableModel getModel(String name) {
        Document doc = getDocument(name);
        List<Column> columns = getComumns(doc);
        Vector<Object[]> vector = getRows(doc, columns);
        Object[] names = columns.toArray(new Column[columns.size()]);
        return new XmlTableModel(names, vector);

    }

    /**
     * 解析指定的xml文件，返回其文档对象
     *
     * @param file
     * @return
     */
    public Document getDocument(String file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(file));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return document;
    }

    /**
     * 解析xml Dom中的列
     *
     * @param doc
     * @return
     */
    private List<Column> getComumns(Document doc) {
        ArrayList<Column> list = new ArrayList<Column>();
        Element root = doc.getRootElement();
        Iterator<Element> el = root.element("columns")
                .elementIterator("column");
        while (el.hasNext()) {
            Element e = (Element) el.next();
            Column c = new Column(e.attributeValue("name"), e
                    .attributeValue("type"));
            list.add(c);
        }
        return list;
    }

    /**
     * 解析xml Dom中的行
     *
     * @param doc
     * @param columns
     * @return
     */
    private Vector<Object[]> getRows(Document doc, List<Column> columns) {
        Vector<Object[]> vector = new Vector<Object[]>();
        Element root = doc.getRootElement();
        List<Node> trList = root.elements("tr");
        for (int i = 0; i < trList.size(); i++) {
            Element tr = (Element) trList.get(i);
            List<Object> list2 = new ArrayList<Object>();
            List<Node> tdList = tr.elements("td");
            for (int j = 0; j < tdList.size(); j++) {
                Element td = (Element) tdList.get(j);
                list2
                        .add(getTDValue(columns.get(j).getType(), td
                                .getTextTrim()));
            }
            vector.add(list2.toArray(new Object[list2.size()]));
        }
        return vector;
    }

    /**
     * 根据列定义的类型，对单元格中的数据进行类型转换
     *
     * @param type
     * @param value
     * @return
     */
    private Object getTDValue(String type, String value) {
        if ("java.lang.Integer".equals(type)) {
            return Integer.parseInt(value);
        }
        if ("java.lang.Boolean".equals(type)) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }

    /**
     * 自定义的表格模型
     * @author Administrator
     *
     */
    class XmlTableModel extends AbstractTableModel {

        private Object[] names;
        private Vector<Object[]> rows;

        public XmlTableModel(Object[] names, Vector<Object[]> rows) {
            this.rows = rows;
            this.names = names;
        }

        @Override
        public int getColumnCount() {
            // TODO Auto-generated method stub
            return names.length;
        }

        @Override
        public int getRowCount() {
            return rows.size();
        }

        @Override
        public Object getValueAt(int row, int col) {
            return rows.get(row)[col];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public String getColumnName(int column) {
            return names[column].toString();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex >= 0 && columnIndex <= getColumnCount()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            rows.get(rowIndex)[columnIndex] = aValue;
        }
    }

    /**
     * 表格列信息实体Bean
     *
     * @author Administrator
     *
     */
    class Column {

        String name;
        String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Column(String name, String type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    //判断是否假期
    public boolean isVacation(){
         //获取系统日期，加工数据为格式XX月XX日
        DateFormat dateFormat = new SimpleDateFormat("M月d日");
        Date date=new Date();
        String nowDate=dateFormat.format(date);
        //初始化是否
        boolean sf=false;
        int rows=new Configure().getModel("lib/jiaqibiao.xml").getRowCount();
        //遍历数据,判断当日是否假期
        for (int i=0;i<rows;i++){
            if(new Configure().getModel("lib/jiaqibiao.xml").getValueAt(i,0).equals(nowDate)){
              if (new Configure().getModel("lib/jiaqibiao.xml").getValueAt(i,1).equals("否")){
                  sf=true;
              }else {
                  sf=false;
              }

            }

        }

        return sf;
    }

    //判断是否日期格式
    public boolean isValidDate(String str){
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
        // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            if (str.equals("")){//输入空不验证

            }else {
                format.setLenient(false);
                format.parse(str);
            }
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }

        return convertSuccess;
    }
    //判断是否正整数
    public boolean isNumeric(String string){
        Pattern pattern = Pattern.compile("[1-9]*");
        return pattern.matcher(string).matches();
    }

    //判断是否为空
    public boolean isKong(String string){
       return string.length()>0;
    }

    /**
     * 根据开始，结束时间计算两个时间段相差多少分钟
     *
     * @param nowDate
     * @param endDate
     * @return
     */
    public  long getDatePoor(String nowDate, String endDate) {
        long nh = 1000 * 60;
        long diff =0;
        Date nowDate1= null;
        try {
            nowDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate1= null;
        try {
            endDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //判断开始，结束时间相差多少秒
        if (nowDate1.getTime()>endDate1.getTime()){
            diff=nowDate1.getTime()-endDate1.getTime();
        }else {
            diff=endDate1.getTime()-nowDate1.getTime();
        }

        //相差多少分子
        return diff/nh;
    }

    /**
     * 根据开始，结束时间判断两个时间段大小
     *
     * @param nowDate
     * @param endDate
     * @return
     */
    public  boolean getDatePoorQH(String nowDate, String endDate) {
        long nh = 1000 * 60;
        Date nowDate1= null;
        try {
            nowDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate1= null;
        try {
            endDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //判断开始，结束时间相差多少秒
        return nowDate1.getTime()<endDate1.getTime();
    }

}