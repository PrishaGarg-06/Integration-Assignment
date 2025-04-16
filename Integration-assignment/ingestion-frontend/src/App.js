import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/LoginPage";         // Page wrapper for Login
import Signup from "./pages/SignupPage";       // Page wrapper for Signup
import SourceSelector from "./components/SourceSelector";
import ColumnSelector from "./components/ColumnSelector";
import RecordReport from "./components/RecordReport";
import Dashboard from "./pages/Dashboard";     // Optional dashboard

const App = () => {
  const token = localStorage.getItem("token");
  const [source, setSource] = useState(null);
  const [columns, setColumns] = useState([]);

  return (
    <Router>
      <Routes>
        {/* Default Redirect */}
        <Route path="/" element={<Navigate to={token ? "/source" : "/login"} />} />

        {/* Auth Pages */}
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />

        {/* Protected Routes */}
        <Route
          path="/source"
          element={token ? <SourceSelector onSelect={setSource} /> : <Navigate to="/login" />}
        />
        <Route
          path="/columns"
          element={token && source ? (
            <ColumnSelector source={source} onComplete={setColumns} />
          ) : (
            <Navigate to="/source" />
          )}
        />
        <Route
          path="/report"
          element={token && source && columns.length ? (
            <RecordReport selectedColumns={columns} source={source} />
          ) : (
            <Navigate to="/columns" />
          )}
        />

        {/* Optional Dashboard */}
        <Route
          path="/dashboard"
          element={token ? <Dashboard /> : <Navigate to="/login" />}
        />
      </Routes>
    </Router>
  );
};

export default App;
