package dao;

import models.Advertisement;
import models.TYPE;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import static utils.DBUtils.getConnection;

public class AdvertisementDaoImpl implements Dao<Advertisement> {

    private static final String INSERT_QUERY = "insert into advertisements(text, user_id, content, type) " +
            "values (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "select * from advertisements";
    private static final String SELECT_APPROVED_ADV_QUERY = "select * from advertisements where approved order by id";
    private static final String SELECT_TO_APPROVE_ADV_QUERY = "select * from advertisements where approved is null order by id";
    private static final String GET_QUERY = "select * from advertisements where id=?";
    private static final String DELETE_QUERY = "delete from advertisements where id=?";
    private static final String UPDATE_QUERY = "update advertisements set text=?, content=?"
            + "where id=?";
    private static final String APPROVE_QUERY = "update advertisements set approved=?"
            + "where id=?";

    private static final Logger LOGGER = Logger.getLogger(AdvertisementDaoImpl.class);

    private Connection connection;

    public AdvertisementDaoImpl() {
        connection = getConnection();
    }


    @Override
    public Advertisement get(long id) {
        Advertisement advertisement = new Advertisement();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);
             ) {
            int i = 0;
            preparedStatement.setInt(++i, (int) id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    advertisement.setId(rs.getInt("id"));
                    advertisement.setCreateDate(rs.getObject("createdate", LocalDateTime.class));
                    advertisement.setText(rs.getString("text"));
                    advertisement.setApproved(rs.getObject("approved", Boolean.class));
                    advertisement.setUser(new UserDaoImpl().get(rs.getInt("user_id")));
                    advertisement.setType(TYPE.valueOf(rs.getString("type")));
                    advertisement.setContent(rs.getString("content"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get advertisement", e);
        }


        return advertisement;
    }


    @Override
    public List<Advertisement> getAll() {
        List<Advertisement> advertisements = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_QUERY)) {

            while (rs.next()) {
                Advertisement advertisement = new Advertisement();
                advertisement.setId(rs.getInt("id"));
                advertisement.setCreateDate(rs.getObject("createdate", LocalDateTime.class));
                advertisement.setText(rs.getString("text"));
                advertisement.setApproved(rs.getObject("approved", Boolean.class));
                advertisement.setUser(new UserDaoImpl().get(rs.getInt("user_id")));
                advertisement.setType(TYPE.valueOf(rs.getString("type")));
                advertisement.setContent(rs.getString("content"));

                advertisements.add(advertisement);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get all advertisements", e);
        }
        return advertisements;
    }

    @Override
    public void add(Advertisement advertisement) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            int i = 0;
            preparedStatement.setString(++i, advertisement.getText());
            preparedStatement.setInt(++i, advertisement.getUser().getId());
            preparedStatement.setString(++i, advertisement.getContent());
            preparedStatement.setString(++i, advertisement.getType().name());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to create advertisement", e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            int i = 0;
            preparedStatement.setInt(++i, (int) id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to delete advertisement", e);
        }
    }

    @Override
    public void update(Advertisement advertisement) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            int i = 0;
            preparedStatement.setString(++i, advertisement.getText());
            preparedStatement.setString(++i, advertisement.getContent());
            preparedStatement.setInt(++i, advertisement.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to update advertisement", e);
        }
    }

    public void setApproved(Advertisement advertisement) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_QUERY)) {
            int i = 0;
            preparedStatement.setBoolean(++i, advertisement.isApproved());
            preparedStatement.setInt(++i, advertisement.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to approve advertisement", e);
        }
    }

    public List<Advertisement> getAllApproved() {
        List<Advertisement> advertisements = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_APPROVED_ADV_QUERY)) {

            while (rs.next()) {
                Advertisement advertisement = new Advertisement();
                advertisement.setId(rs.getInt("id"));
                advertisement.setCreateDate(rs.getObject("createdate", LocalDateTime.class));
                advertisement.setText(rs.getString("text"));
                advertisement.setApproved(rs.getObject("approved", Boolean.class));
                advertisement.setUser(new UserDaoImpl().get(rs.getInt("user_id")));
                advertisement.setType(TYPE.valueOf(rs.getString("type")));
                advertisement.setContent(rs.getString("content"));

                advertisements.add(advertisement);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get all approved advertisements", e);
        }
        return advertisements;
    }

    public List<Advertisement> getAllAdvToApprove() {
        List<Advertisement> advertisements = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_TO_APPROVE_ADV_QUERY)) {

            while (rs.next()) {
                Advertisement advertisement = new Advertisement();
                advertisement.setId(rs.getInt("id"));
                advertisement.setCreateDate(rs.getObject("createdate", LocalDateTime.class));
                advertisement.setText(rs.getString("text"));
                advertisement.setApproved(rs.getObject("approved", Boolean.class));
                advertisement.setUser(new UserDaoImpl().get(rs.getInt("user_id")));
                advertisement.setType(TYPE.valueOf(rs.getString("type")));
                advertisement.setContent(rs.getString("content"));

                advertisements.add(advertisement);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get all advertisements to approve", e);
        }
        return advertisements;
    }


}
