import React, { useState } from 'react';

function Ingestion({ columns, onStartIngestion }) {
  const [selectedTable, setSelectedTable] = useState('');

  const handleTableChange = (e) => {
    setSelectedTable(e.target.value);
  };

  const handleIngest = () => {
    if (selectedTable) {
      onStartIngestion(selectedTable);
    }
  };

  return (
    <div>
      <select onChange={handleTableChange}>
        <option value="">Select ClickHouse Table</option>
        <option value="table1">Table 1</option>
        <option value="table2">Table 2</option>
        {/* Add dynamic tables based on your actual schema */}
      </select>
      <button onClick={handleIngest}>Start Ingestion</button>
    </div>
  );
}

export default Ingestion;
