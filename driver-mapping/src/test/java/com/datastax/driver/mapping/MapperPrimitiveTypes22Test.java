package com.datastax.driver.mapping;

import com.datastax.driver.core.CCMBridge;
import com.datastax.driver.core.DateWithoutTime;
import com.datastax.driver.core.utils.Bytes;
import com.datastax.driver.core.utils.CassandraVersion;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import org.testng.annotations.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;

import static org.testng.Assert.assertEquals;

@CassandraVersion(major=2.2)
public class MapperPrimitiveTypes22Test extends CCMBridge.PerClassSingleNodeCluster {

    protected Collection<String> getTableDefinitions() {
        return Arrays.asList("CREATE TABLE primitiveTypes22 ("
                        + "byteBufferCol blob primary key,"
                        + "dateWithoutTimeCol date,"
                        + "timeCol time, timeWrapperCol time,"
                        + "byteCol tinyint, byteWrapperCol tinyint,"
                        + "shortCol smallint, shortWrapperCol smallint)");
    }

    @Test(groups = "short")
    public void testWriteRead22() throws Exception {
        ByteBuffer byteBufferCol = Bytes.fromHexString("0xCAFEBABE");
        DateWithoutTime dateWithoutTimeCol = DateWithoutTime.fromMillisSinceEpoch(System.currentTimeMillis());
        long timeCol = 123456789L;
        Long timeWrapperCol = 123456789L;
        byte byteCol = 42;
        Byte byteWrapperCol = 42;
        short shortCol = 16384;
        Short shortWrapperCol = 16384;

        PrimitiveTypes22 primitiveTypes = new PrimitiveTypes22();
        primitiveTypes.setByteBufferCol(byteBufferCol);
        primitiveTypes.setDateWithoutTimeCol(dateWithoutTimeCol);
        primitiveTypes.setTimeCol(timeCol);
        primitiveTypes.setTimeWrapperCol(timeWrapperCol);
        primitiveTypes.setByteCol(byteCol);
        primitiveTypes.setByteWrapperCol(byteWrapperCol);
        primitiveTypes.setShortCol(shortCol);
        primitiveTypes.setShortWrapperCol(shortWrapperCol);

        Mapper<PrimitiveTypes22> mapper = new MappingManager(session).mapper(PrimitiveTypes22.class);
        mapper.save(primitiveTypes);
        PrimitiveTypes22 primitiveTypes2 = mapper.get(byteBufferCol);

        assertEquals(primitiveTypes2.getByteBufferCol(), byteBufferCol);
        assertEquals(primitiveTypes2.getDateWithoutTimeCol(), dateWithoutTimeCol);
        assertEquals(primitiveTypes2.getTimeCol(), timeCol);
        assertEquals(primitiveTypes2.getTimeWrapperCol(), timeWrapperCol);
        assertEquals(primitiveTypes2.getByteCol(), byteCol);
        assertEquals(primitiveTypes2.getByteWrapperCol(), byteWrapperCol);
        assertEquals(primitiveTypes2.getShortCol(), shortCol);
        assertEquals(primitiveTypes2.getShortWrapperCol(), shortWrapperCol);
    }


    @Table(name = "primitiveTypes22")
    public static class PrimitiveTypes22 {
        @PartitionKey
        private ByteBuffer byteBufferCol;
        private DateWithoutTime dateWithoutTimeCol;
        private long timeCol;
        private Long timeWrapperCol;
        private byte byteCol;
        private Byte byteWrapperCol;
        private short shortCol;
        private Short shortWrapperCol;

        public ByteBuffer getByteBufferCol() {
            return byteBufferCol;
        }

        public void setByteBufferCol(ByteBuffer byteBufferCol) {
            this.byteBufferCol = byteBufferCol;
        }

        public DateWithoutTime getDateWithoutTimeCol() {
            return dateWithoutTimeCol;
        }

        public void setDateWithoutTimeCol(DateWithoutTime dateWithoutTimeCol) {
            this.dateWithoutTimeCol = dateWithoutTimeCol;
        }

        public long getTimeCol() {
            return timeCol;
        }

        public void setTimeCol(long timeCol) {
            this.timeCol = timeCol;
        }

        public Long getTimeWrapperCol() {
            return timeWrapperCol;
        }

        public void setTimeWrapperCol(Long timeWrapperCol) {
            this.timeWrapperCol = timeWrapperCol;
        }

        public byte getByteCol() {
            return byteCol;
        }

        public void setByteCol(byte byteCol) {
            this.byteCol = byteCol;
        }

        public Byte getByteWrapperCol() {
            return byteWrapperCol;
        }

        public void setByteWrapperCol(Byte byteWrapperCol) {
            this.byteWrapperCol = byteWrapperCol;
        }

        public short getShortCol() {
            return shortCol;
        }

        public void setShortCol(short shortCol) {
            this.shortCol = shortCol;
        }

        public Short getShortWrapperCol() {
            return shortWrapperCol;
        }

        public void setShortWrapperCol(Short shortWrapperCol) {
            this.shortWrapperCol = shortWrapperCol;
        }
    }
}