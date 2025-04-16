package com.clickfile.ingestiontool.model;

public class IngestionRequest {
    private ConnectionRequest connectionRequest;
    private String tableName;
    private String filePath;

    public ConnectionRequest getConnectionRequest() {
        return connectionRequest;
    }

    public void setConnectionRequest(ConnectionRequest connectionRequest) {
        this.connectionRequest = connectionRequest;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
