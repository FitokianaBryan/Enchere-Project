/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.ObjectBdd;

/**
 *
 * @author toavi
 */
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class ManipDb {/* this class is for all database manipulations */

    private final static String[] sortKeyWords = {"order", "group by", "limit"};

    public static Connection connect(String driverName, String user, String password, String dsn) throws Exception {
        Connection con = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(dsn, user, password);
            if (con == null) {
                Exception e = new Exception("An error occured during the attempt to connnect...");
                throw e;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return con;
    }

    public static Connection pgConnect(String user, String database, String password) throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://containers-us-west-23.railway.app:7851/" + database;
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public static PreparedStatement createInsertStatement(Object obj, Connection con) {
        HashMap<String, Object> insertionValues = ManipClass.getInsertionValues(obj);
        String columnsValues = " values(";
        String columns = "(";
        String request = "Insert into " + obj.getClass().getSimpleName();
        Set<String> keys = insertionValues.keySet();
        String[] attribNames = keys.toArray(new String[keys.size()]);
        Object[] values = new Object[keys.size()];
        for (int i = 0; i < attribNames.length; i++) {
            if (i != 0) {
                columns += ", ";
                columnsValues += ", ";
            }
            if (attribNames[i].equals("id")) {

                columnsValues += "CONCAT('" + obj.getClass().getSimpleName() + "-'," + "nextval('" + obj.getClass().getSimpleName() + "Seq'))";
                columns += attribNames[i];
            } else {
                columnsValues += "?";
                columns += attribNames[i];
            }
            values[i] = insertionValues.get(attribNames[i]);
        }
        columnsValues += ")";
        columns += ")";
        request = request + columns + columnsValues;
        PreparedStatement sql = null;
        try {
            sql = con.prepareStatement(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ManipClass.fillStatement(sql, obj.getClass(), values);
        return sql;
    }

    public static PreparedStatement createSelfUpdateStatement(Object instance, Connection con, Object filter) {
        HashMap<String, Object> updateValues = ManipClass.getInsertionValues(instance);
        String request = "Update " + instance.getClass().getSimpleName() + " set ";
        Set<String> keys = updateValues.keySet();
        String[] attribNames = keys.toArray(new String[keys.size()]);
        Vector<Object> data = new Vector<Object>(1, 1);
        for (int i = 0; i < attribNames.length; i++) {
            if (!attribNames[i].equals("id")) {
                if (i < attribNames.length - 1) {
                    request += attribNames[i] + " = ? ,";
                } else {
                    request += attribNames[i] + " = ?";
                }
                data.add(updateValues.get(attribNames[i]));
            }
        }
        if (request.endsWith(",")) {
            request = request.substring(0, request.lastIndexOf(",") - 1);
        }
        List<Object> filtreValues = ManipClass.generateIdCondition(filter);
        String conditionString = (String) filtreValues.get(0);
        Object[] conditionValues = (Object[]) filtreValues.get(1);
        request = request + conditionString;
        PreparedStatement sql = null;
        try {
            System.out.println(request);
            sql = con.prepareStatement(request);
            fillUpdateStatement(sql, data.toArray(), conditionValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

    public static void fillUpdateStatement(PreparedStatement statement, Object[] updateData, Object[] updateCondition) {
        int nextIndex = ManipClass.fillUpdateData(statement, updateData);
        ManipClass.fillUpdateCondition(statement, updateCondition, nextIndex);
    }

    public static PreparedStatement createGeneralUpdateStatement(Object instance, Connection con, Object filtre) {
        HashMap<String, Object> updateValues = ManipClass.getInsertionValues(instance);
        String request = "Update " + instance.getClass().getSimpleName() + " set ";
        Set<String> keys = updateValues.keySet();
        String[] attribNames = keys.toArray(new String[keys.size()]);
        Vector<Object> data = new Vector<Object>(1, 1);
        for (int i = 0; i < attribNames.length; i++) {
            if (!attribNames[i].equals("id")) {
                if (i < attribNames.length - 1) {
                    request += attribNames[i] + " = ? ,";
                } else {
                    request += attribNames[i] + " = ?";
                }
                data.add(updateValues.get(attribNames[i]));
            }
        }
        List<Object> filtreValues = ManipClass.generateCondition(filtre);
        String conditionString = (String) filtreValues.get(0);
        Object[] conditionValues = (Object[]) filtreValues.get(1);
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(request + conditionString);
            fillUpdateStatement(statement, data.toArray(), conditionValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static PreparedStatement createSelectStatement(Object filtre, Connection con, String extra) {
        String request = "Select * from " + filtre.getClass().getSimpleName();
        List<Object> filtreValues = ManipClass.generateCondition(filtre);
        String conditionString = (String) filtreValues.get(0);
        Object[] conditionValues = (Object[]) filtreValues.get(1);
        request += conditionString + extra;
        PreparedStatement statement = null;
        try {
            System.out.println(request);
            statement = con.prepareStatement(request);
            ManipClass.fillInsertCondition(statement, conditionValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static Object[] fetchData(ResultSet res, Class objClass) throws Exception {
        HashMap<String, Class> attribInfo = ManipClass.getAttributes(objClass);
        Set<String> keys = attribInfo.keySet();
        String[] attribNames = keys.toArray(new String[keys.size()]);
        Method[] setters = ManipClass.getSetters(objClass, attribInfo);
        Vector vec = new Vector<Object>();
        try {
            while (res.next()) {
                Object newInstance = null;
                Object[] rowValues = new Object[attribNames.length];
                for (int i = 0; i < attribNames.length; i++) {
                    rowValues[i] = ManipClass.fetchSqlData(res, attribInfo.get(attribNames[i]), attribNames[i]);
                }
                newInstance = toObject(rowValues, objClass, setters);
                vec.add(newInstance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            res.close();
        }
        return vec.toArray();
    }

    public static Object toObject(Object[] values, Class objClass, Method[] setters) {
        Object instance = null;
        try {
            instance = objClass.newInstance();
            for (int i = 0; i < setters.length; i++) {
                ManipClass.callSetter(instance, values[i], setters[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static PreparedStatement createGeneralSelectStatement(Object filtre, Connection con,
            String extraConditition) {
        String request = "Select * from " + filtre.getClass().getSimpleName();
        List<Object> filtreValues = ManipClass.generateCondition(filtre);
        String conditionString = (String) filtreValues.get(0);
        Object[] conditionValues = (Object[]) filtreValues.get(1);
        if (!conditionString.equals("")) {
            request += conditionString;
        }
        if (!extraConditition.equals("")) {
            if (!conditionString.equals("")) {
                request += " and " + extraConditition;
            } else {
                request += " where " + extraConditition;
            }
        }
        PreparedStatement statement = null;
        try {
            System.out.println(request);
            statement = con.prepareStatement(request);
            ManipClass.fillInsertCondition(statement, conditionValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    private static boolean isSortQuery(String query) {
        for (String key : sortKeyWords) {
            if (query.contains(key)) {
                return true;
            }
        }
        return false;
    }

    public static PreparedStatement createGeneralSelectFromViewStatement(Object filtre, Connection con, String viewName,
            String extraCondition) {
        String request = "Select * from " + viewName;
        List<Object> filtreValues = ManipClass.generateCondition(filtre);
        String conditionString = (String) filtreValues.get(0);
        Object[] conditionValues = (Object[]) filtreValues.get(1);
        if (!conditionString.equals("")) {
            request += conditionString;
        }
        if (!extraCondition.equals("")) {
            if (isSortQuery(extraCondition)) {
                request += " " + extraCondition;
            } else {
                if (!conditionString.equals("")) {
                    request += " and " + extraCondition;
                } else {
                    request += " " + extraCondition;
                }
            }
        }
        PreparedStatement statement = null;
        try {
            System.out.println(request);
            statement = con.prepareStatement(request);
            ManipClass.fillInsertCondition(statement, conditionValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static String getActualId(String className, Connection con) throws Exception {
        String request = "Select Concat('%s',currval('%s')) as currentId";
        request = String.format(request, className + "-", "sq"+className);
        PreparedStatement statement = con.prepareStatement(request);
        ResultSet res = statement.executeQuery();
        String id = "";
        while (res.next()) {
            id = res.getString("currentId");
        }
        return id;
    }
}
