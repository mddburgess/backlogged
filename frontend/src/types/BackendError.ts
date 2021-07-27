export interface BackendError {
    timestamp: string,
    path: string
    status: number,
    error: string,
    message: string,
    trace?: string,
}
