import React, { useEffect, useState } from 'react';
import { getColumns } from '../services/api'; // Import the API call

function ColumnSelector({ source, onComplete }) {
  const [columns, setColumns] = useState([]);
  const [selected, setSelected] = useState([]);

  useEffect(() => {
    const fetchColumns = async () => {
      try {
        const data = await getColumns(source);
        setColumns(data);
      } catch (error) {
        console.error('Error fetching columns:', error);
      }
    };

    fetchColumns();
  }, [source]);

  const toggleColumn = (column) => {
    setSelected((prevSelected) =>
      prevSelected.includes(column)
        ? prevSelected.filter((col) => col !== column)
        : [...prevSelected, column]
    );
  };

  const handleNext = () => {
    if (selected.length > 0) {
      onComplete(selected);
    } else {
      alert("Please select at least one column.");
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Select Columns for Ingestion</h2>
      {columns.length === 0 ? (
        <p>Loading columns...</p>
      ) : (
        <div style={{ marginBottom: '20px' }}>
          {columns.map((column) => (
            <div key={column}>
              <label>
                <input
                  type="checkbox"
                  checked={selected.includes(column)}
                  onChange={() => toggleColumn(column)}
                />
                {column}
              </label>
            </div>
          ))}
        </div>
      )}
      <button onClick={handleNext} disabled={selected.length === 0}>
        Next
      </button>
    </div>
  );
}

export default ColumnSelector;
