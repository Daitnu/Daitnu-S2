export interface BusinessErrorResponse {
  status: number;
  message: string;
  code: string | null;
  errors: Array<FieldError> | null;
}

interface FieldError {
  field: string;
  value: string;
  reason: string;
}
