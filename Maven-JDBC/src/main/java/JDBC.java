
import java.sql.*;

public class JDBC {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/db1";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "123123";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        // name of tables
        String table1 = "author";
        String mid_table = "books_author";
        String table2 = "books";
        try {
            //STEP 2: Register JDBC driver -> DriverManager
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            //STEP 4: Execute a query (insert / delete / update / select queries)

            // insert new books data
            System.out.println("Creating statement...");
            // for books table
            String insert = "(6,'AB',2008),(7,'notAbook',2030)";
            String sql1 = "INSERT INTO " + table2 + " VALUES " + insert + ";";
            stmt = conn.prepareStatement(sql1);
            stmt.execute();
            // for joint table (relationship table)
            insert = "(6,1),(6,4)";
            sql1 = "INSERT INTO " + mid_table + " VALUES " + insert + ";";
            stmt = conn.prepareStatement(sql1);
            stmt.execute();

            // delete
            sql1 = "DELETE FROM " + table2 + " where id = 7;";
            stmt = conn.prepareStatement(sql1);
            stmt.execute();

            // update
            sql1 = "UPDATE " + table2 + " SET publish_year = 2012 where id = 6;";
            stmt = conn.prepareStatement(sql1);
            stmt.execute();

            // select
            sql1 = "select a.name, a.sex, b.title, b.publish_year from author a,books b,books_author ab " +
                    "where a.id = ab.author_id and ab.book_id = b.id;";
            stmt = conn.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String title = rs.getString("title");
                int year  = rs.getInt("publish_year");

                //Display values
                System.out.print("author_name: " + name);
                System.out.print(", sex: " + sex);
                System.out.print(", title: " + title);
                System.out.println(", publish_year: " + year);
            }
            conn.commit();
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main

}
