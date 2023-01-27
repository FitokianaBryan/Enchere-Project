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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class ManipClass {

    /* use for reflection methods */

    public static String capitalize(String str) {
        if (str == null) {
            return "";
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static HashMap<String, Class> getAttributes(Class objClass) {
        Field[] fields = objClass.getDeclaredFields();
        HashMap<String, Class> attribTypes = new HashMap<String, Class>();
        for (int i = 0; i < fields.length; i++) {
            attribTypes.put(fields[i].getName(), fields[i].getType());
        }
        return attribTypes;
    }

    public static Method[] getSetters(Class objClass, HashMap<String, Class> attribTypes) {
        Set<String> keys = attribTypes.keySet();
        String[] attributes = keys.toArray(new String[keys.size()]);
        Method[] methods = new Method[attributes.length];
        try {
            for (int i = 0; i < attributes.length; i++) {
                methods[i] = objClass.getMethod("set" + capitalize(attributes[i]), attribTypes.get(attributes[i]));
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Un des attributs n'a pas de setter");
            e.printStackTrace();
        }
        return methods;
    }

    public static Method[] getGetters(Class objClass, HashMap<String, Class> attribTypes) {
        Set<String> keys = attribTypes.keySet();
        String[] attributes = keys.toArray(new String[keys.size()]);
        Method[] methods = new Method[attributes.length];
        try {
            for (int i = 0; i < attributes.length; i++) {
                methods[i] = objClass.getMethod("get" + capitalize(attributes[i]));
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Un des attributs n'a pas de getter");
            e.printStackTrace();
        }
        return methods;
    }

    public static HashMap<String, Object> getInsertionValues(Object obj) {

        Class objClass = obj.getClass();
        HashMap<String, Object> insertionValues = new HashMap<String, Object>();
        HashMap<String, Class> attributes = getAttributes(objClass);
        Set<String> keys = attributes.keySet();
        String[] stringKeys = keys.toArray(new String[keys.size()]);
        Method[] methods = getGetters(objClass, attributes);
        try {
            for (int i = 0; i < methods.length; i++) {
                insertionValues.put(stringKeys[i], methods[i].invoke(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertionValues;

    }

    /*
     * public static HashMap<String, Object> getFilterValues(Object filter){
     * HashMap<String, Object> allFilterValues = getInsertionValues(filter); //
     * for(int i=0;i<) }
     */
    public static void fillStatement(PreparedStatement statement, Class objClass, Object[] values) {
        HashMap<String, Class> attribTypes = getAttributes(objClass);
        Set<String> keys = attribTypes.keySet();
        String[] attributes = keys.toArray(new String[keys.size()]);
        try {
            int parametreIndex = 1;
            for (int i = 0; i < values.length; i++) {
                if (attribTypes.get(attributes[i]).getSimpleName().equalsIgnoreCase("int")
                        || attribTypes.get(attributes[i]).getSimpleName().equalsIgnoreCase("Integer")) {
                    statement.setInt(parametreIndex, (int) values[i]);
                    parametreIndex++;
                } else if (attribTypes.get(attributes[i]).getSimpleName().equals("float")
                        || attribTypes.get(attributes[i]).getSimpleName().equals("Float")) {
                    statement.setFloat(parametreIndex, (float) values[i]);
                    parametreIndex++;
                } else if (attribTypes.get(attributes[i]).getSimpleName().equals("double")
                        || attribTypes.get(attributes[i]).getSimpleName().equals("Double")) {
                    statement.setDouble(parametreIndex, (double) values[i]);
                    parametreIndex++;
                } else if (attribTypes.get(attributes[i]).getSimpleName().equalsIgnoreCase("String")) {
                    if (!attributes[i].equals("id")) {
                        statement.setString(parametreIndex, (String) values[i]);
                        parametreIndex++;
                    }

                } else if (attribTypes.get(attributes[i]).getSimpleName().equals("Date")) {
                    statement.setDate(parametreIndex, toSqlDate((java.util.Date) values[i]));
                    parametreIndex++;
                } else if (attribTypes.get(attributes[i]).getSimpleName().equals("Calendar")) {
                    statement.setDate(parametreIndex, toSqlDate((Calendar) values[i]));
                    parametreIndex++;
                } else if (attribTypes.get(attributes[i]).getSimpleName().equals("Time")) {
                    statement.setTime(parametreIndex, (Time) values[i]);
                    parametreIndex++;
                }
                else if (attribTypes.get(attributes[i]).getSimpleName().equals("Timestamp")) {
                    statement.setTimestamp(parametreIndex, (Timestamp) values[i]);
                    parametreIndex++;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Date toSqlDate(java.util.Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

    public static Date toSqlDate(Calendar date) {
        return new Date(date.get(date.YEAR) - 1900, date.get(date.MONTH), date.get(date.DAY_OF_MONTH));
    }

    public static java.util.Date toDate(java.sql.Date daty) {
        return new java.util.Date(daty.getYear(), daty.getMonth(), daty.getDate());
    }

    public static int fillUpdateData(PreparedStatement statement, Object[] values) {
        int firstIndex = 1;
        try {
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    statement.setInt(firstIndex, (Integer) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Double) {
                    statement.setDouble(firstIndex, (Double) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Float) {
                    statement.setFloat(firstIndex, (Float) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof java.sql.Date) {
                    statement.setDate(firstIndex, toSqlDate((java.sql.Date) values[i]));
                    firstIndex++;
                } else if (values[i] instanceof java.util.Date) {
                    statement.setDate(firstIndex, toSqlDate((java.util.Date) values[i]));
                    firstIndex++;
                } else if (values[i] instanceof Calendar) {
                    statement.setDate(firstIndex, toSqlDate((Calendar) values[i]));
                    firstIndex++;
                } else if (values[i] instanceof String) {
                    statement.setString(firstIndex, (String) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Time) {
                    statement.setTime(firstIndex, (Time) values[i]);
                    firstIndex++;
                }
                else if (values[i] instanceof Timestamp) {
                    statement.setTimestamp(firstIndex, (Timestamp) values[i]);
                    firstIndex++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstIndex;
    }

    public static void fillUpdateCondition(PreparedStatement statement, Object[] values, int firstIndex) {
        try {
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    statement.setInt(firstIndex, (Integer) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Double) {
                    statement.setDouble(firstIndex, (Double) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Float) {
                    statement.setFloat(firstIndex, (Float) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof java.sql.Date) {
                    statement.setDate(firstIndex, toSqlDate((java.sql.Date) values[i]));
                    firstIndex++;
                }  else if (values[i] instanceof java.util.Date) {
                    statement.setDate(firstIndex, toSqlDate((java.util.Date) values[i]));
                    firstIndex++;
                }else if (values[i] instanceof Calendar) {
                    statement.setDate(firstIndex, toSqlDate((Calendar) values[i]));
                    firstIndex++;
                } else if (values[i] instanceof String) {
                    statement.setString(firstIndex, (String) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Time) {
                    statement.setTime(firstIndex, (Time) values[i]);
                    firstIndex++;
                }
                else if (values[i] instanceof Timestamp) {
                    statement.setTimestamp(firstIndex, (Timestamp) values[i]);
                    firstIndex++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValide(Object obj) {
        boolean ret = true;
        if (obj instanceof Number) {
            Number num = (Number) obj;
            if (num.intValue() == 0) {
                return false;
            } else if (num.doubleValue() == 0.0) {
                return false;
            } else {
                return true;
            }
        } else if (obj instanceof String) {
            String temp = (String) obj;
            if (temp.equalsIgnoreCase("")) {
                return false;
            } else {
                return true;
            }
        } else if (obj == null) {
            return false;
        }
        return ret;
    }

    public static HashMap<String, Object> getConditionValues(Object filtre) {
        HashMap<String, Class> attribTypes = getAttributes(filtre.getClass());
        Set<String> keys = attribTypes.keySet();
        String[] attribNames = keys.toArray(new String[keys.size()]);
        Method[] methods = getGetters(filtre.getClass(), attribTypes);
        Object[] rawValues = new Object[methods.length];
        try {
            for (int i = 0; i < methods.length; i++) {
                rawValues[i] = methods[i].invoke(filtre, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterConditionValues(rawValues, attribNames);
    }

    public static HashMap<String, Object> filterConditionValues(Object[] rawValues, String[] attribNames) {
        HashMap<String, Object> validAttributes = new HashMap<String, Object>();
        for (int i = 0; i < rawValues.length; i++) {
            if (isValide(rawValues[i])) {
                validAttributes.put(attribNames[i], rawValues[i]);
            }
        }
        return validAttributes;
    }

    public static List<Object> generateCondition(Object filtre) {
        HashMap<String, Object> validAttributes = getConditionValues(filtre);
        Set<String> keys = validAttributes.keySet();
        String[] attribNames = keys.toArray(new String[keys.size()]);
        String request = "";
        Object[] values = new Object[attribNames.length];
        for (int i = 0; i < attribNames.length; i++) {
            if (i < attribNames.length - 1) {
                request += attribNames[i] + " = ? and ";
            } else {
                request += attribNames[i] + " = ?";
            }
            values[i] = validAttributes.get(attribNames[i]);
        }
        if (!request.equals("")) {
            request = " where " + request;
        }
        List<Object> retValues = new ArrayList<Object>();
        retValues.add(request);
        retValues.add(values);
        return retValues;
    }

    public static List<Object> generateIdCondition(Object filtre){
        HashMap<String, Object> validAttributes = getConditionValues(filtre);
        Set<String> keys = validAttributes.keySet();
        String[] attribNames = keys.toArray(new String[keys.size()]);
        String request = "";
        Object[] values = new Object[1];
        for(String attribName: attribNames){
            if(attribName.equals("id")){
                request += attribName + " = ?";
                values[0] = validAttributes.get(attribName);
            }
        }
        if (!request.equals("")) {
            request = " where " + request;
        }
        List<Object> retValues = new ArrayList<Object>();
        retValues.add(request);
        retValues.add(values);
        return retValues;
    }

    public static void fillInsertCondition(PreparedStatement statement, Object[] values) {
        int firstIndex = 1;
        try {
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    statement.setInt(firstIndex, (Integer) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Double) {
                    statement.setDouble(firstIndex, (Double) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Float) {
                    statement.setFloat(firstIndex, (Float) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof Date) {
                    statement.setDate(firstIndex, toSqlDate((Date) values[i]));
                    firstIndex++;
                } else if (values[i] instanceof Calendar) {
                    statement.setDate(firstIndex, toSqlDate((Calendar) values[i]));
                    firstIndex++;
                } else if (values[i] instanceof Time) {
                    statement.setTime(firstIndex, (Time) values[i]);
                    firstIndex++;
                } else if (values[i] instanceof String) {
                    statement.setString(firstIndex, (String) values[i]);
                    firstIndex++;
                }
                else if (values[i] instanceof Timestamp) {
                    statement.setTimestamp(firstIndex, (Timestamp) values[i]);
                    firstIndex++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callSetter(Object instance, Object value, Method setter) {
        try {
            if (value instanceof Integer) {
                setter.invoke(instance, (Integer) value);
            } else if (value instanceof Double) {
                setter.invoke(instance, (Double) value);
            } else if (value instanceof Float) {
                setter.invoke(instance, (Float) value);
            } else if (value instanceof java.sql.Date) {
                setter.invoke(instance, (java.sql.Date) value);
            } else if (value instanceof java.util.Date) {
                setter.invoke(instance, (java.util.Date) value);
            } else if (value instanceof Calendar) {
                setter.invoke(instance, (Calendar) value);
            } else if (value instanceof String) {
                setter.invoke(instance, (String) value);
            } else if (value instanceof Time) {
                setter.invoke(instance, (Time) value);
            }
            else if (value instanceof Timestamp) {
                setter.invoke(instance, (Timestamp) value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object fetchSqlData(ResultSet res, Class objClass, String attribName) throws Exception {
        String className = objClass.getSimpleName();
        if (className.equals("Integer") || className.equals("int")) {
            return res.getInt(attribName);
        } else if (className.equals("Float") || className.equals("float")) {
            return res.getFloat(attribName);
        } else if (className.equals("Double") || className.equals("double")) {
            return res.getDouble(attribName);
        } else if (className.equals("String")) {
            return res.getString(attribName);
        } else if (className.equals("Date")) {
            return toDate(res.getDate(attribName));
        } else if (className.equals("Calendar")) {
            return res.getDate(attribName, Calendar.getInstance());
        } else if (className.equals("Timestamp")) {
            return res.getTimestamp(attribName);
        }else {
            return null;
        }
    }
}
