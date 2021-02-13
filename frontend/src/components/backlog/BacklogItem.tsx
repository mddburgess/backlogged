import React from "react";
import {ListGroupItem} from "react-bootstrap";
import BacklogToggle from "./BacklogToggle";
import {Backlog} from "../../types/Backlog";

const BacklogItem = ({backlog}: Props) => (
    <ListGroupItem className="px-2 py-1">
        <div className="d-flex justify-content-between">
            {backlog.title.name}
            <div>
                <BacklogToggle title={backlog.title}/>
            </div>
        </div>
    </ListGroupItem>
);

interface Props {
    backlog: Backlog;
}

export default BacklogItem;
