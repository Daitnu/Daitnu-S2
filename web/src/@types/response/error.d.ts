export interface BusinessErrorResponse {
  status: number;
  message: string;
  code: string;
  errors: Array<FieldError> | null;
}

interface FieldError {
  field: string;
  value: string;
  reason: string;
}
