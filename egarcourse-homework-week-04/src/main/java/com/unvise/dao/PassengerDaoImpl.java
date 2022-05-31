package com.unvise.dao;

import com.unvise.db.PostgresHelper;
import com.unvise.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class PassengerDaoImpl implements PassengerDao {

    private static PassengerDaoImpl instance;
    private static final String SELECT_ALL_QUERY = "SELECT * FROM passenger";
    private static final String SELECT_TICKETS_QUERY = "SELECT * FROM ticket t " +
            "JOIN ticket_passenger tp ON tp.ticket_id = t.id " +
            "WHERE tp.passenger_id = ?";
    private static final String SELECT_FLIGHTS_QUERY = "SELECT * FROM flight f " +
            "JOIN ticket t ON t.flight_id = f.id " +
            "WHERE t.id = ?";
    private static final String SELECT_AIRPLANE_QUERY = "SELECT * FROM airplane ap " +
            "JOIN flight f ON f.airplane_id = ap.id " +
            "WHERE f.id = ?";
    private static final String SELECT_AIRLINE_QUERY = "SELECT * FROM airline al " +
            "JOIN airplane ap ON ap.airline_id = al.id " +
            "WHERE ap.id = ?";
    private static final String SELECT_PASSENGER_BY_ID = "SELECT * FROM passenger WHERE id = ?";
    private static final String SAVE_PASSENGER_QUERY = "INSERT INTO passenger(first_name, last_name, phone) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_PASSENGER_QUERY = "UPDATE passenger " +
            "SET first_name = ?, last_name = ?, phone = ? WHERE id = ?";
    private static final String DELETE_PASSENGER_QUERY = "DELETE FROM passenger WHERE id = ?";

    private PassengerDaoImpl() {
    }

    public static PassengerDaoImpl getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PassengerDaoImpl();
        }
        return instance;
    }

    @Override
    public List<PassengerEntity> findAll() {
        return query(connection -> {
            try (connection) {
                PreparedStatement ps = connection.prepareStatement(SELECT_ALL_QUERY);

                List<PassengerEntity> passengerEntities = new ArrayList<>();
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    PassengerEntity passenger = getNewPassengerFromResultSet(connection, rs);
                    passengerEntities.add(passenger);
                }
                return passengerEntities;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Optional<PassengerEntity> findById(Integer id) {
        return query(connection -> {
            try (connection) {
                PreparedStatement ps = connection.prepareStatement(SELECT_PASSENGER_BY_ID);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                PassengerEntity passenger = null;
                if (rs.next()) {
                    passenger = getNewPassengerFromResultSet(connection, rs);
                }
                return Optional.ofNullable(passenger);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void save(PassengerEntity passenger) {
        query(connection -> {
            try (connection) {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(SAVE_PASSENGER_QUERY);

                ps.setString(1, passenger.getFirstName());
                ps.setString(2, passenger.getLastName());
                ps.setString(3, passenger.getPhone());
                ps.execute();

                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @Override
    public void update(PassengerEntity passenger) {
        query(connection -> {
            try (connection) {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(UPDATE_PASSENGER_QUERY);

                ps.setString(1, passenger.getFirstName());
                ps.setString(2, passenger.getLastName());
                ps.setString(3, passenger.getPhone());
                ps.setInt(4, passenger.getId());
                ps.execute();

                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @Override
    public void delete(PassengerEntity passenger) {
        query(connection -> {
            try (connection) {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(DELETE_PASSENGER_QUERY);

                ps.setInt(1, passenger.getId());
                ps.execute();

                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    private <T> T query(Function<Connection, T> s) {
        try (Connection conn = PostgresHelper.getConnection()) {
            return s.apply(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PassengerEntity getNewPassengerFromResultSet(Connection connection, ResultSet rs) throws SQLException {
        PassengerEntity passenger = new PassengerEntity();

        passenger.setId(rs.getInt("id"));
        passenger.setFirstName(rs.getString("first_name"));
        passenger.setLastName(rs.getString("last_name"));
        passenger.setPhone(rs.getString("phone"));
        passenger.setTicketEntityList(selectTickets(connection, passenger));

        for (TicketEntity ticket : passenger.getTicketEntityList()) {
            ticket.setFlightEntity(selectFlight(connection, ticket));
        }
        return passenger;
    }

    private List<TicketEntity> selectTickets(Connection connection, PassengerEntity passenger) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_TICKETS_QUERY);

        ps.setInt(1, passenger.getId());

        List<TicketEntity> ticketEntities = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TicketEntity ticket = new TicketEntity();
            ticket.setId(rs.getInt("id"));
            ticket.setFare(rs.getDouble("fare"));
            ticket.setCurrency(rs.getString("currency"));
            ticket.setExpired(rs.getBoolean("expired"));

            ticketEntities.add(ticket);
        }
        return ticketEntities;
    }

    private FlightEntity selectFlight(Connection connection, TicketEntity ticket) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_FLIGHTS_QUERY);

        ps.setInt(1, ticket.getId());

        FlightEntity flight = new FlightEntity();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            flight.setId(rs.getInt("id"));
            flight.setFrom(rs.getString("from"));
            flight.setTo(rs.getString("to"));
            flight.setDepartureTime(rs.getTime("departure_time"));
            flight.setArrivalTime(rs.getTime("arrival_time"));
            flight.setAirplaneEntity(selectAirplane(connection, flight));
        }
        return flight;
    }

    private AirplaneEntity selectAirplane(Connection connection, FlightEntity flight) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_AIRPLANE_QUERY);

        ps.setInt(1, flight.getId());

        AirplaneEntity airplane = new AirplaneEntity();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            airplane.setId(rs.getInt("id"));
            airplane.setModel(rs.getString("model"));
            airplane.setLicensePlate(rs.getString("license_plate"));
            airplane.setAirlineEntity(selectAirline(connection, airplane));
        }
        return airplane;
    }

    private AirlineEntity selectAirline(Connection connection, AirplaneEntity airplane) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_AIRLINE_QUERY);

        ps.setInt(1, airplane.getId());

        AirlineEntity airline = new AirlineEntity();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            airline.setId(rs.getInt("id"));
            airline.setName(rs.getString("name"));
            airline.setPhone(rs.getString("phone"));
        }
        return airline;
    }

}
