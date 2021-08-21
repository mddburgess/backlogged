import {ListGroup} from "react-bootstrap";
import API from "api";
import NewBacklogItem from "components/backlog/NewBacklogItem";
import {useState} from "react";
import {BacklogItem} from "types/BacklogItem";
import BacklogItemRow from "components/backlog/BacklogItemRow";
import BacklogItemModal from "components/backlog/BacklogItemModal";

const BacklogItemList = () => {
    const {data} = API.useListBacklogQuery();
    const [updateBacklogItem] = API.useUpdateBacklogItemMutation();
    const [deleteBacklogItem] = API.useDeleteBacklogItemMutation();

    const [backlogItem, setBacklogItem] = useState<BacklogItem>();

    return (
        <ListGroup>
            {data?.map(item => (<BacklogItemRow backlogItem={item} onClick={setBacklogItem}/>))}
            <NewBacklogItem/>
            <BacklogItemModal
                title="Edit Backlog Item"
                backlogItem={backlogItem}
                setBacklogItem={setBacklogItem}
                onSave={updateBacklogItem}
                onDelete={deleteBacklogItem}
            />
        </ListGroup>
    );
};

export default BacklogItemList;
