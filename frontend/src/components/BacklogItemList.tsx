import {Button, ListGroup, ListGroupItem} from "react-bootstrap";
import API from "api";
import NewBacklogItem from "components/NewBacklogItem";

const BacklogItemList = () => {
    const {data} = API.useListBacklogQuery();
    const [deleteBacklogItem] = API.useDeleteBacklogItemMutation();

    return (
        <ListGroup>
            {data?.map(item => (
                <ListGroupItem key={item.id} className="d-flex justify-content-between">
                    {item.name}
                    <Button onClick={() => deleteBacklogItem(item.id ?? 0)}>Delete</Button>
                </ListGroupItem>
            ))}
            <NewBacklogItem />
        </ListGroup>
    );
};

export default BacklogItemList;
