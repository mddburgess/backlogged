export interface BacklogItem {
    id: number,
    name: string,
    type: string,
    status: string,
    activityTime?: string,
    activities?: Activity[]
}

export interface Activity {
    type: string,
    id: number,
    activityDate: string,
    fromStatus: string,
    toStatus: string,
    duration: string
}
