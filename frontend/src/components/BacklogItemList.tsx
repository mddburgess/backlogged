import {ListGroup} from "react-bootstrap";
import API from "api";
import NewBacklogItem from "components/NewBacklogItem";
import {useState} from "react";
import EditBacklogItemModal from "components/backlog/EditBacklogItemModal";
import {BacklogItem} from "types/BacklogItem";
import BacklogItemRow from "components/backlog/BacklogItemRow";

const BacklogItemList = () => {
    const {data} = API.useListBacklogQuery();

    const [backlogItem, setBacklogItem] = useState<BacklogItem>();

    return (
        <ListGroup>
            {data?.map(item => (<BacklogItemRow backlogItem={item} onClick={setBacklogItem}/>))}
            <NewBacklogItem />
            <EditBacklogItemModal backlogItem={backlogItem} setBacklogItem={setBacklogItem} />
        </ListGroup>
    );
};

export default BacklogItemList;
