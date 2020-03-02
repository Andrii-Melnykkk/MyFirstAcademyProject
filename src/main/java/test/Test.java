package test;


import dao.AdvertisementDaoImpl;
import dao.UserDaoImpl;

public class Test {


    public static void main(String[] args)  {

        UserDaoImpl userDao = new UserDaoImpl();
        userDao.delete(13);



    }
}





