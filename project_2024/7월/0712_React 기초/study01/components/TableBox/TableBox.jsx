const TableBox = () => {
  return (
    <div className="tableBox">
      <table border="1">
        <tr>
          <th colspan="3">Table 연습</th>
        </tr>
        <tr>
          <th>seq</th>
          <th>name</th>
          <th>price</th>
        </tr>
        <tr>
          <td>1</td>
          <td>Apple</td>
          <td>1000</td>
        </tr>
      </table>
    </div>
  );
};

export default TableBox;
