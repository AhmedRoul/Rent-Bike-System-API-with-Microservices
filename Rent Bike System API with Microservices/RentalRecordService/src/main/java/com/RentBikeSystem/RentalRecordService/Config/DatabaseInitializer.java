package com.RentBikeSystem.RentalRecordService.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

    }
    /*
    def distance(lat1, lon1, lat2, lon2):
    r = 6371 # km
            p = pi / 180

    a = 0.5 - cos((lat2-lat1)*p)/2 + cos(lat1*p) * cos(lat2*p) * (1-cos((lon2-lon1)*p))/2
            return 2 * r * asin(sqrt(a))

    */
    private void createGetCalculateDistanceFunction()
    {
        String code=
                "Create or replace function CalculateDistance" +
                "(lat1 Decimal (9,2 ) ,lon1  Decimal (9,2 ),lat2  Decimal (9,2 ) ,lon2 Decimal (9,2 ) )"+
                "return real " +
                "language plpgsql AS $$"+
                "DECLARE"+
                "distance  real;"+
                "r real  :=6371 ;"+
                "p real:= PI()/180; "+
                "a real;"+
                 "BEGIN  "+
                "a:=.5 - COS((lat2-lat1)*p)/2 +COS(lat1*p)*COS(lat2*p) * (1-cos((lon2-lon1)*p))/2"+
                "distance:= 2 * r * ASIN(SQRT(a))"+
                "RETURN distance"+
                "End;"+
                "$$;";
        try {
            jdbcTemplate.execute(code);
            System.out.println("Function GetPointCountByCity created successfully.");
        }
        catch (Exception e) {
            System.err.println("Error creating function: " + e.getMessage());
        }

    }
}
