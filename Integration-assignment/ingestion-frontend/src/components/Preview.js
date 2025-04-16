import React, { useEffect, useState } from 'react';

function Preview({ file }) {
  const [data, setData] = useState(null);

  useEffect(() => {
    const reader = new FileReader();
    reader.onload = () => {
      const content = reader.result;
      // You would need to parse CSV, JSON, etc., depending on the file format.
      const rows = content.split('\n').map((line) => line.split(','));
      setData(rows);
    };
    reader.readAsText(file);
  }, [file]);

  if (!data) return <div>Loading Preview...</div>;

  return (
    <table>
      <thead>
        <tr>
          {data[0].map((header, index) => (
            <th key={index}>{header}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.slice(1).map((row, index) => (
          <tr key={index}>
            {row.map((cell, index) => (
              <td key={index}>{cell}</td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default Preview;
