import {Form, ListGroupItem} from "react-bootstrap";
import API from "api";
import {FormEvent, useState} from "react";

const NewBacklogItem = () => {
    const [name, setName] = useState("");
    const [createBacklogItem] = API.useCreateBacklogItemMutation();

    const onSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        createBacklogItem({
            name,
            type: "VIDEO_GAME",
            status: "NEW"
        });
    }

    return (
        <ListGroupItem>
            <Form onSubmit={onSubmit}>
                <Form.Control onChange={event => setName(event.target.value)} />
            </Form>
        </ListGroupItem>
    );
};

export default NewBacklogItem;
