import {ListGroupItem} from "react-bootstrap";
import {useState} from "react";
import {BacklogItem} from "types/BacklogItem";
import BacklogItemModal from "components/backlog/BacklogItemModal";
import API from "api";

const NewBacklogItem = () => {
    const [backlogItem, setBacklogItem] = useState<Partial<BacklogItem>>();
    const [createBacklogItem] = API.useCreateBacklogItemMutation();

    return (
        <ListGroupItem action onClick={() => setBacklogItem({})}>
            Add new item...
            <BacklogItemModal
                title="New Backlog Item"
                backlogItem={backlogItem}
                setBacklogItem={setBacklogItem}
                onSave={createBacklogItem}
            />
        </ListGroupItem>
    );
};

export default NewBacklogItem;
