import styles from "./List.module.css";

const List = ({ datas }) => {
  return (
    //   <h3>{content}</h3>
    <table border={1}>
      <thead>
        <tr>
          <th>Id</th>
          <th>name</th>
          <th>email</th>
          <th>role</th>
        </tr>
      </thead>
      <tbody>
        {datas.map((data, i) => {
          return (
            <tr key={i}>
              <td>{data.id}</td>
              <td>{data.name}</td>
              <td>{data.email}</td>
              <td>{data.role}</td>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default List;
