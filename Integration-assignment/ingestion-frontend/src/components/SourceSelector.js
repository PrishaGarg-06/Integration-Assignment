import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const SourceSelector = ({ onSelect }) => {
  const [selectedSource, setSelectedSource] = useState(null);
  const navigate = useNavigate();

  const handleNext = () => {
    if (selectedSource) {
      onSelect(selectedSource);          // Update the selected source in App state
      navigate("/columns");              // Navigate to column selection
    } else {
      alert("Please select a source file");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Select Data Source</h2>
      <div>
        <label>
          <input
            type="radio"
            name="source"
            value="file1.csv"
            onChange={() => setSelectedSource("file1.csv")}
          />
          File 1 (file1.csv)
        </label>
      </div>
      <div>
        <label>
          <input
            type="radio"
            name="source"
            value="file2.csv"
            onChange={() => setSelectedSource("file2.csv")}
          />
          File 2 (file2.csv)
        </label>
      </div>

      <button onClick={handleNext} style={{ marginTop: "20px", padding: "10px 20px" }}>
        Next
      </button>
    </div>
  );
};

export default SourceSelector;
