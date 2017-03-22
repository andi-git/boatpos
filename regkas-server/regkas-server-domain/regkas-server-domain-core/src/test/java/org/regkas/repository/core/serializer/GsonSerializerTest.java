package org.regkas.repository.core.serializer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GsonSerializerTest {

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    private String serialized = "{\"TheString\":\"aString\",\"TheNumber\":5,\"TheTime\":\"2015-07-01T12:15:45\"}";

    @Test
    public void testSerialize() {
        assertEquals(serialized, serializer.serialize(new BeanToSerialize("aString", 5, LocalDateTime.of(2015, 7, 1, 12, 15, 45))));
    }

    @Test
    public void testDeserialize() {
        BeanToSerialize beanToSerialize = serializer.deserialize(serialized, BeanToSerialize.class);
        assertEquals("aString", beanToSerialize.getString());
        assertEquals(5, beanToSerialize.getNumber());
        assertEquals(LocalDateTime.of(2015, 7, 1, 12, 15, 45), beanToSerialize.getDateTime());
    }

    public static class BeanToSerialize {

        @SerializedName("TheString")
        @Expose
        private String string;

        @SerializedName("TheNumber")
        @Expose
        private int number;

        @SerializedName("TheTime")
        @Expose
        private LocalDateTime dateTime;

        public BeanToSerialize(String string, int number, LocalDateTime dateTime) {
            this.string = string;
            this.number = number;
            this.dateTime = dateTime;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }
    }
}
