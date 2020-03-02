package test;


import dao.AdvertisementDaoImpl;
import dao.UserDaoImpl;

public class Test {


    public static void main(String[] args)  {
        AdvertisementDaoImpl adv = new AdvertisementDaoImpl();
        adv.delete(41);
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.delete(11);



    }
}





