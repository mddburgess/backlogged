import {BacklogItem} from "types/BacklogItem";
import {Badge, ListGroupItem} from "react-bootstrap";
import {BacklogItemTypeIcon} from "components/backlog/BacklogItemTypeIcon";
import {Duration} from "luxon";

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
        {backlogItem.activityTime && Duration.fromISO(backlogItem.activityTime).toFormat("h'h' mm'm'")}
    </ListGroupItem>
);

export default BacklogItemRow;
