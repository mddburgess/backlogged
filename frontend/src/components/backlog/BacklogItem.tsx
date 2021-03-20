import React from "react";
import { ListGroupItem } from "react-bootstrap";
import { Backlog } from "../../types/Backlog";
import RemoveFromBacklogButton from "./RemoveFromBacklogButton";

const BacklogItem = ({ backlog }: Props) => (
  <ListGroupItem className="px-2 py-1">
    <div className="d-flex justify-content-between">
      {backlog.title.name}
      <div>
        <RemoveFromBacklogButton backlog={backlog} />
      </div>
    </div>
  </ListGroupItem>
);

interface Props {
  backlog: Backlog;
}

export default BacklogItem;
