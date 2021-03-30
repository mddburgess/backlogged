import React from "react";
import { Link } from "react-router-dom";
import { ListGroupItem } from "react-bootstrap";
import { Title } from "../../types/Title";
import BacklogToggle from "../backlog/BacklogToggle";

interface Props {
  title: Title;
}

const TitleRow = ({ title }: Props) => (
  <ListGroupItem className="px-2 py-1">
    <div className="d-flex justify-content-between">
      <Link to={`/edit/${title.key}`}>{title.name}</Link>
      <div>
        <BacklogToggle title={title} />
      </div>
    </div>
    <ul className="mb-0">
      {title.copies.map((copy) => (
        <small>
          <li>
            {copy.platform} - {copy.service}
          </li>
        </small>
      ))}
    </ul>
  </ListGroupItem>
);

export default TitleRow;
