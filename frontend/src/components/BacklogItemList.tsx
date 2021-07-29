import {ListGroup} from "react-bootstrap";
import API from "api";
import NewBacklogItem from "components/NewBacklogItem";
import {useState} from "react";
import {EditBacklogItemModal} from "components/backlog/EditBacklogItemModal";
import {BacklogItem} from "types/BacklogItem";

const BacklogItemList = () => {
    const {data} = API.useListBacklogQuery();

    const [backlogItem, setBacklogItem] = useState<BacklogItem>();

    return (
        <ListGroup>
            {data?.map(item => (
                <ListGroup.Item key={item.id} action className="d-flex justify-content-between"
                    onClick={() => setBacklogItem(item)}>
                    {item.name}
                </ListGroup.Item>
            ))}
            <NewBacklogItem />
            <EditBacklogItemModal backlogItem={backlogItem} setBacklogItem={setBacklogItem} />
        </ListGroup>
    );
};

export default BacklogItemList;
