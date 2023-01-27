package com.example.demo.connex;
import java.sql.*;

public class Connexion
{
    Connection con;
    public Statement stat;
    ResultSet res;
    protected static String url = "jdbc:postgresql://containers-us-west-23.railway.app:7851/railway";
/*    protected static String Host = "containers-us-west-23.railway.app";
    protected static String Port = "7851";
    protected static String DB = "railway";*/
    protected static String Username = "postgres";
    protected static String Password = "qz8iclqVZnx87GipdO80";


    public Connexion(String req)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url, Username, Password);
            this.stat= this.con.createStatement();
//  			this.res=stat.executeQuery(req);
            stat.execute(req);
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public Connexion()
    {
        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url, Username, Password);
        } catch (Exception e) {
        } finally {
        }
    }


    public Connexion(String req,String ide)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url, Username, Password);
            this.stat= this.con.createStatement();
            this.res=stat.executeQuery(req);
            //	stat.execute(req);
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public PreparedStatement prepareStatement(String query) throws SQLException{
        return this.con.prepareStatement(query);
    }
    public ResultSet getResultset()
    {
        return this.res;
    }
    public void getCommit() throws Exception
    {
        this.stat.executeQuery("commit");
    }
    public void getRollBack() throws Exception
    {
        this.stat.executeQuery("rollback");
    }
    public Statement getStat()
    {
        return this.stat;
    }
}

