import {BacklogItem} from "types/BacklogItem";
import {Badge, ListGroupItem} from "react-bootstrap";
import {BacklogItemTypeIcon} from "components/backlog/BacklogItemTypeIcon";

type Props = {
    backlogItem: BacklogItem,
    onClick: (backlogItem: BacklogItem) => void
}

const BacklogItemRow = ({backlogItem, onClick}: Props) => (
    <ListGroupItem
        key={backlogItem.id}
        action
        onClick={() => onClick(backlogItem)}>
        <BacklogItemTypeIcon backlogItem={backlogItem}/>
        {backlogItem.name}
        <Badge className="ms-1" bg="secondary">{backlogItem.status}</Badge>
    </ListGroupItem>
);

export default BacklogItemRow;
