import React from "react";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();

  const handleStartIngestion = () => {
    navigate("/source");
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <h1>Welcome to the Ingestion Tool Dashboard</h1>
      <p>This is your starting point for ingesting data from flat files into ClickHouse.</p>

      <button onClick={handleStartIngestion} style={{ margin: "10px", padding: "10px 20px" }}>
        Start Ingestion
      </button>
      <br />
      <button onClick={handleLogout} style={{ margin: "10px", padding: "10px 20px", backgroundColor: "tomato", color: "#fff" }}>
        Logout
      </button>
    </div>
  );
};

export default Dashboard;
