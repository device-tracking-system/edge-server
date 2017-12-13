package pl.edu.agh.iet.dts.edge.messaging.format;

/**
 * @author Bartłomiej Grochal
 */
public class GPSEvent {

    private final String id;

    private final double latitude;
    private final double longitude;


    public GPSEvent() {
        this(null, 0.0d, 0.0d);
    }

    public GPSEvent(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("{\n")
                .append("\tid : ").append(id).append(",\n")
                .append("\tlatitude : ").append(latitude).append(",\n")
                .append("\tlongitude : ").append(longitude).append("\n")
                .append("}");
        return builder.toString();
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
