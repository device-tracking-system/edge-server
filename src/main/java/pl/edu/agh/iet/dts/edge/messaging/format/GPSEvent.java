package pl.edu.agh.iet.dts.edge.messaging.format;

/**
 * @author Bartłomiej Grochal
 */
public class GPSEvent {

    private final String ownerId;

    private final double latitude;
    private final double longitude;
    private final long timestamp;


    public GPSEvent() {
        this(null, 0.0d, 0.0d, -1L);
    }

    public GPSEvent(final String ownerId, final double latitude, final double longitude, final long timestamp) {
        this.ownerId = ownerId;

        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("{\n")
                .append("\townerId : ").append(ownerId).append(",\n")
                .append("\tlatitude : ").append(latitude).append(",\n")
                .append("\tlongitude : ").append(longitude).append(",\n")
                .append("\ttimestamp : ").append(timestamp).append("\n")
                .append("}");
        return builder.toString();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
