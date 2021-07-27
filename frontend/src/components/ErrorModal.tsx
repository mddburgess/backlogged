import {Modal} from "react-bootstrap";
import {useAppDispatch, useAppSelector} from "store/hooks";
import {errorActions} from "store/error";

const ErrorModal = () => {
    const error = useAppSelector(state => state.error);
    const dispatch = useAppDispatch();

    return (
        <Modal show={error.show} onHide={() => dispatch(errorActions.clear())} size="xl">
            <Modal.Header closeButton>
                <Modal.Title className="text-danger">
                    {error?.data?.status} {error?.data?.error}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body className="text-danger">
                <dl>
                    <dt>Path</dt>
                    <dd>{error?.data?.path}</dd>
                    <dt>Message</dt>
                    <dd>{error?.data?.message}</dd>
                    {error?.data?.trace && <>
                        <dt>Stacktrace</dt>
                        <dd><small><pre>{error?.data?.trace}</pre></small></dd>
                    </>}
                </dl>
            </Modal.Body>
        </Modal>
    );
};

export default ErrorModal;
